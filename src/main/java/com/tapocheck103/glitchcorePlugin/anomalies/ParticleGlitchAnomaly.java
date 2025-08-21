package com.tapocheck103.glitchcorePlugin.anomalies;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Random;

public class ParticleGlitchAnomaly {
    private final Random random = new Random();

    // Вызов из ивента (например при ломании блока)
    public void trigger(Player player, Block block) {
        if (!GlitchcorePlugin.getInstance().getConfigManager().isEnabled("particles")) return;

        int chance = GlitchcorePlugin.getInstance().getConfigManager().getParticleChance();
        if (random.nextInt(100) < chance) {
            player.spawnParticle(
                    Particle.BLOCK,
                    block.getLocation().add(0.5, 0.5, 0.5),
                    30,
                    0.3, 0.3, 0.3,
                    Material.BEDROCK.createBlockData()
            );
        }
    }

    // Вызов через команду (без блока, просто на игроке)
    public void trigger(Player player) {
        if (!GlitchcorePlugin.getInstance().getConfigManager().isEnabled("particles")) return;

        player.spawnParticle(
                Particle.BLOCK,
                player.getLocation().add(0, 1, 0),
                30,
                0.3, 0.3, 0.3,
                Material.BEDROCK.createBlockData()
        );
    }
}
