package com.tapocheck103.glitchcorePlugin.listeners;

import com.tapocheck103.glitchcorePlugin.ConfigManager;
import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import com.tapocheck103.glitchcorePlugin.utils.EffectUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class ScreenEffectListener implements Listener {
    private final ConfigManager config;

    public ScreenEffectListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        // шанс "глюка экрана"
        if (RandomUtils.chance(0.01)) {
            EffectUtils.giveRandomEffect(PotionEffectType.DARKNESS, 40, 0); // 2 сек тошноты
        }
    }
}
