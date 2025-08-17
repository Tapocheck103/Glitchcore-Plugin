package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GlitchyToolsListener implements Listener {
    private final ConfigManager config;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public GlitchyToolsListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onToolUse(PlayerInteractEvent event) {
        if (event.getItem() == null) return;

        Material item = event.getItem().getType();
        String name = item.name();
        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        // проверка кулдауна
        if (cooldowns.containsKey(uuid) &&
                now - cooldowns.get(uuid) < config.getToolFailCooldown() * 1000L) {
            return;
        }

        // проверяем категории предметов (все инструменты и оружие от дерева до незерита)
        if (name.endsWith("_SWORD") ||
                name.endsWith("_PICKAXE") ||
                name.endsWith("_AXE") ||
                name.endsWith("_SHOVEL") ||
                name.endsWith("_HOE")) {

            if (RandomUtils.chance(config.getToolFailChance())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cВаш " + name.toLowerCase() + " внезапно отказал!");
                cooldowns.put(uuid, now); // ставим кулдаун
            }
        }
    }
}
