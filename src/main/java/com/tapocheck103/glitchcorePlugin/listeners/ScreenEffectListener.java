package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.EffectUtils;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScreenEffectListener implements Listener {
    private final ConfigManager config;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public ScreenEffectListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        long now = System.currentTimeMillis();

        if (cooldowns.containsKey(uuid) &&
                now - cooldowns.get(uuid) < config.getScreenEffectCooldown() * 1000L) {
            return;
        }

        if (RandomUtils.chance(config.getScreenEffectChance())) {
            EffectUtils.giveRandomEffect(PotionEffectType.DARKNESS, 40, 0);
            cooldowns.put(uuid, now);
        }
    }
}
