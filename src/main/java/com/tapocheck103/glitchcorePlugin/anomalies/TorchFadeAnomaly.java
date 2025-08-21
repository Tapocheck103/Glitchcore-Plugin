package com.tapocheck103.glitchcorePlugin.anomalies;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class TorchFadeAnomaly {
    public void trigger(Player player) {
        List<Block> torches = new ArrayList<>();
        Location loc = player.getLocation();

        int radius = 20; // радиус поиска факелов
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = loc.getWorld().getBlockAt(
                            loc.getBlockX() + x,
                            loc.getBlockY() + y,
                            loc.getBlockZ() + z
                    );
                    if (block.getType() == Material.TORCH) {
                        torches.add(block);
                        block.setType(Material.AIR); // факел пропадает
                    }
                }
            }
        }

        // вернуть факела через 5 секунд
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Block block : torches) {
                    if (block.getType() == Material.AIR) {
                        block.setType(Material.TORCH);
                    }
                }
            }
        }.runTaskLater(GlitchcorePlugin.getInstance(), 100L); // 100 тиков = 5 секунд
    }
}
