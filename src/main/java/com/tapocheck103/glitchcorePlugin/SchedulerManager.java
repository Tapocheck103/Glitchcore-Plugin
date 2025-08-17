package com.tapocheck103.glitchcorePlugin;

import com.tapocheck103.glitchcorePlugin.utils.RandomUtils;
import com.tapocheck103.glitchcorePlugin.utils.EffectUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class SchedulerManager {
    private final GlitchcorePlugin plugin;
    private final ConfigManager config;
    private BukkitTask task;

    public SchedulerManager(GlitchcorePlugin plugin, ConfigManager config) {
        this.plugin = plugin;
        this.config = config;
    }

    public void start() {
        task = plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            Player random = EffectUtils.getRandomPlayer();
            if (random == null) return;

            if (RandomUtils.chance(0.02)) {
                random.sendMessage("<UnknownPlayer> Hello.");
            }

            if (RandomUtils.chance(0.01)) {
                random.playSound(random.getLocation(), "entity.ghast.scream", 1.0f, 1.0f);
            }

        }, 20L, 200L); // проверка каждые 10 сек
    }

    public void stop() {
        if (task != null) task.cancel();
    }
}
