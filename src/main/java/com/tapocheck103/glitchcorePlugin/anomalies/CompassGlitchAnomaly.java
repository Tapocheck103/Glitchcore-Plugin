package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CompassGlitchAnomaly {
    public void trigger(Player player) {
        World world = Bukkit.getWorld("world");
        if (world != null) {
            Location fakeLoc = new Location(world, 100, 70, 100);
            player.setCompassTarget(fakeLoc);
        }
    }
}
