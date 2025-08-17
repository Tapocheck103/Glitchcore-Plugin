package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PhantomDamageListener implements Listener {
    private final ConfigManager config;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public PhantomDamageListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        if (cooldowns.containsKey(uuid) &&
                now - cooldowns.get(uuid) < config.getPhantomDamageCooldown() * 1000L) {
            return;
        }

        if (RandomUtils.chance(config.getPhantomDamageChance())) {
            event.getPlayer().damage(1.0); // пол сердца
            event.getPlayer().sendMessage("§7Вы почувствовали странное воздействие...");
            cooldowns.put(uuid, now);
        }
    }
}
