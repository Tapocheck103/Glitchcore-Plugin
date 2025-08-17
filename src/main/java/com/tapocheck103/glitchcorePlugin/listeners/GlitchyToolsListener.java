package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GlitchyToolsListener implements Listener {
    private final ConfigManager config;

    public GlitchyToolsListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onToolUse(PlayerInteractEvent event) {
        if (event.getItem() == null) return;

        Material item = event.getItem().getType();

        // Разрешаем "глючить" только инструменты и оружие
        if (item.name().endsWith("_SWORD") ||
                item.name().endsWith("_PICKAXE") ||
                item.name().endsWith("_AXE") ||
                item.name().endsWith("_SHOVEL") ||
                item.name().endsWith("_HOE")) {

            if (RandomUtils.chance(config.getToolFailChance())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cВаш " + item.name().toLowerCase() + " внезапно отказал!");
            }
        }
    }
}
