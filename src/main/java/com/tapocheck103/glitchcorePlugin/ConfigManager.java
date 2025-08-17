package com.tapocheck103.glitchcorePlugin;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final GlitchcorePlugin plugin;
    private final FileConfiguration config;

    public ConfigManager(GlitchcorePlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public boolean isFakeNicknamesEnabled() { return config.getBoolean("player.fakenicknames", true); }
    public boolean isBlockLaggingEnabled() { return config.getBoolean("player.blocklagging", true); }
    public boolean isGlitchyToolsEnabled() { return config.getBoolean("player.glitchytools", true); }
    public boolean isPhantomDamageEnabled() { return config.getBoolean("player.phantomdamage", true); }

    public double getFakeNickChance() { return config.getDouble("settings.fake_nick_chance", 0.05); }
    public double getPhantomDamageChance() { return config.getDouble("settings.phantom_damage_chance", 0.02); }
    public double getToolFailChance() { return config.getDouble("settings.tool_fail_chance", 0.1); }
}
