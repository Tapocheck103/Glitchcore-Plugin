package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PhantomDamageListener implements Listener {
    private final ConfigManager config;

    public PhantomDamageListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (RandomUtils.chance(config.getPhantomDamageChance())) {
            event.getPlayer().damage(1.0); // пол сердца
            event.getPlayer().sendMessage("§7Вы почувствовали странное воздействие...");
        }
    }
}
