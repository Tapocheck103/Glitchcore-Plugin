package com.tapocheck103.glitchcorePlugin.config;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;

public class ConfigManager {
    private final GlitchcorePlugin plugin;

    public ConfigManager(GlitchcorePlugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
    }

    public boolean isEnabled(String anomaly) {
        return plugin.getConfig().getBoolean("settings.anomalies." + anomaly + ".enabled", true);
    }

    public int getParticleChance() {
        return plugin.getConfig().getInt("settings.anomalies.particles.chance", 20);
    }

    public void reload() {
        plugin.reloadConfig();
    }
}
