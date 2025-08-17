package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlockLagListener implements Listener {
    private final ConfigManager config;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public BlockLagListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        // кулдаун на лаги блоков
        if (cooldowns.containsKey(uuid) &&
                now - cooldowns.get(uuid) < config.getBlockLagCooldown() * 1000L) {
            return;
        }

        if (RandomUtils.chance(config.getBlockLagChance())) {
            Block block = event.getBlock();
            Material type = block.getType();

            // через 2 секунды блок вернется на место
            Bukkit.getScheduler().runTaskLater(
                    GlitchcorePlugin.getInstance(),
                    () -> {
                        if (block.getType() == Material.AIR) {
                            block.setType(type);
                        }
                    },
                    40L
            );

            // ставим кулдаун
            cooldowns.put(uuid, now);
        }
    }
}
