package com.tapocheck103.glitchcorePlugin.anomalies;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FakeNickAnomaly {
    public void trigger(Player target, String fakeName) {
        Location loc = target.getLocation().add(0, 2, 0);
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setCustomNameVisible(true);
            s.setCustomName("§7" + fakeName); // берём тот ник, что указали в команде
            s.setMarker(true);
        });

        // исчезает через 3 секунды (60 тиков)
        new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
            }
        }.runTaskLater(GlitchcorePlugin.getInstance(), 60L);
    }
}
