package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundGlitchAnomaly {
    public void trigger(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1f, 1f);
    }
}
