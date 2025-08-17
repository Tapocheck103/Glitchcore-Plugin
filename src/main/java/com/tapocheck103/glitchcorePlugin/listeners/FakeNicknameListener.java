package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeNicknameListener implements Listener {
    private final ConfigManager config;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public FakeNicknameListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        if (cooldowns.containsKey(uuid) &&
                now - cooldowns.get(uuid) < config.getFakeNickCooldown() * 1000L) {
            return;
        }

        if (RandomUtils.chance(config.getFakeNickChance())) {
            Location loc = event.getPlayer().getLocation().add(2, 0, 0);
            ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class, s -> {
                s.setVisible(false);
                s.setCustomNameVisible(true);
                s.setCustomName("Player" + uuid.toString().substring(0, 5));
                s.setMarker(true);
            });

            Bukkit.getScheduler().runTaskLater(
                    GlitchcorePlugin.getInstance(),
                    stand::remove,
                    60L
            );

            cooldowns.put(uuid, now);
        }
    }
}
