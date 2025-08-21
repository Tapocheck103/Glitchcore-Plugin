package com.tapocheck103.glitchcorePlugin.anomalies;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleGlitchAnomaly {
    public void trigger(Player player) {
        // создаём частицы блока бедрока
        player.spawnParticle(
                Particle.BLOCK,                  // правильный тип для блоковых частиц
                player.getLocation(),            // где спавнить
                20,                              // количество
                0.5, 1, 0.5,                     // разброс по X/Y/Z
                0.1,                             // "скорость"
                org.bukkit.Material.BEDROCK.createBlockData() // какие частицы (бедрок)
        );
    }
}
