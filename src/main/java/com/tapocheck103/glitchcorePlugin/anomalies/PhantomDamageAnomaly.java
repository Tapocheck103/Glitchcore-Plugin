package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.entity.Player;

public class PhantomDamageAnomaly {
    public void trigger(Player player) {
        player.damage(1.0); // снимает 0.5 сердца
    }
}
