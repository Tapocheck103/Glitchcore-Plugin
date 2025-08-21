package com.tapocheck103.glitchcorePlugin.anomalies;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CompassGlitchAnomaly {
    private final Random random = new Random();

    public void trigger(Player player) {
        // Запоминаем исходную цель компаса (обычно spawn)
        Location originalTarget = player.getCompassTarget();
        World world = player.getWorld();

        // Рандомный эффект: 50% дрожание стрелки, 50% указывает в рандомное место
        if (random.nextBoolean()) {
            // Эффект "дрожания" стрелки
            new BukkitRunnable() {
                int ticks = 0;

                @Override
                public void run() {
                    if (ticks >= 100) { // 5 секунд (20 тиков * 5)
                        player.setCompassTarget(originalTarget); // вернуть обратно
                        cancel();
                        return;
                    }
                    // случайная цель поблизости ±50 блоков
                    Location loc = player.getLocation().clone().add(
                            random.nextInt(100) - 50,
                            random.nextInt(20) - 10,
                            random.nextInt(100) - 50
                    );
                    player.setCompassTarget(loc);
                    ticks += 10;
                }
            }.runTaskTimer(GlitchcorePlugin.getInstance(), 0L, 10L); // каждые 10 тиков (0.5 сек)
        } else {
            // Эффект "указание на фантомную точку"
            Location fakeTarget = player.getLocation().clone().add(
                    random.nextInt(500) - 250,
                    0,
                    random.nextInt(500) - 250
            );
            player.setCompassTarget(fakeTarget);

            // Вернуть обратно через 5 секунд
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setCompassTarget(originalTarget);
                }
            }.runTaskLater(GlitchcorePlugin.getInstance(), 100L); // 100 тиков = 5 сек
        }
    }
}
