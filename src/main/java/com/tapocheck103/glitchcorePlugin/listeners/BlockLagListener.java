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

public class BlockLagListener implements Listener {
    private final ConfigManager config;

    public BlockLagListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!config.isBlockLaggingEnabled()) return;

        // 50% шанс "лагнуть" блок
        if (RandomUtils.chance(0.5)) {
            Block block = event.getBlock();
            Material type = block.getType();

            Bukkit.getScheduler().runTaskLater(
                    GlitchcorePlugin.getInstance(),
                    () -> {
                        if (block.getType() == Material.AIR) {
                            block.setType(type);
                        }
                    },
                    40L // 2 секунды
            );
        }
    }
}
