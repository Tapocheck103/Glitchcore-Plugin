package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomTeleportAnomaly {
    public void trigger(Player player) {
        Random rand = new Random();
        Location loc = player.getLocation().add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
        player.teleport(loc);
    }
}
