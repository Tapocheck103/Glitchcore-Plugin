package com.tapocheck103.glitchcorePlugin.anomalies;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeNickAnomaly {
    private final Random random = new Random();

    public void trigger(Player target, String fakeName) {
        Location baseLoc = target.getLocation();

        // Все 8 направлений по кругу в радиусе 5 блоков
        List<Location> possibleSpots = new ArrayList<>();
        int radius = 5;

        possibleSpots.add(baseLoc.clone().add(radius, 0, 0));   // восток
        possibleSpots.add(baseLoc.clone().add(-radius, 0, 0));  // запад
        possibleSpots.add(baseLoc.clone().add(0, 0, radius));   // юг
        possibleSpots.add(baseLoc.clone().add(0, 0, -radius));  // север

        possibleSpots.add(baseLoc.clone().add(radius, 0, radius));    // юго-восток
        possibleSpots.add(baseLoc.clone().add(radius, 0, -radius));   // северо-восток
        possibleSpots.add(baseLoc.clone().add(-radius, 0, radius));   // юго-запад
        possibleSpots.add(baseLoc.clone().add(-radius, 0, -radius));  // северо-запад

        // Выбираем случайное место из списка
        Location spawnLoc = possibleSpots.get(random.nextInt(possibleSpots.size()));
        spawnLoc.setY(baseLoc.getY()); // остаёмся на том же уровне по высоте

        // Создаём фантомный ник
        ArmorStand stand = spawnLoc.getWorld().spawn(spawnLoc, ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setCustomNameVisible(true);
            s.setCustomName("§7" + fakeName);
            s.setMarker(true);
            s.setGravity(false);
        });

        // Удаляем через 3 секунды
        new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
            }
        }.runTaskLater(GlitchcorePlugin.getInstance(), 60L);
    }
}
