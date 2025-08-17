package com.tapocheck103.glitchcorePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;

public class ConfigManager {
    private final GlitchcorePlugin plugin;
    private final FileConfiguration config;

    public ConfigManager(GlitchcorePlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    // включение/выключение
    public boolean isFakeNicknamesEnabled() {
        return config.getBoolean("player.fakenicknames", true);
    }

    public boolean isBlockLaggingEnabled() {
        return config.getBoolean("player.blocklagging", true);
    }

    public boolean isGlitchyToolsEnabled() {
        return config.getBoolean("player.glitchytools", true);
    }

    public boolean isPhantomDamageEnabled() {
        return config.getBoolean("player.phantomdamage", true);
    }

    public boolean isScreenEffectsEnabled() {
        return config.getBoolean("player.screeneffects", true);
    }

    // шансы и кулдауны
    public double getFakeNickChance() { return config.getDouble("settings.fake_nick_chance", 0.05); }
    public int getFakeNickCooldown() { return config.getInt("settings.fake_nick_cooldown", 30); }

    public double getPhantomDamageChance() { return config.getDouble("settings.phantom_damage_chance", 0.02); }
    public int getPhantomDamageCooldown() { return config.getInt("settings.phantom_damage_cooldown", 15); }

    public double getToolFailChance() { return config.getDouble("settings.tool_fail_chance", 0.1); }
    public int getToolFailCooldown() { return config.getInt("settings.tool_fail_cooldown", 10); }

    public double getScreenEffectChance() { return config.getDouble("settings.screeneffect_chance", 0.01); }
    public int getScreenEffectCooldown() { return config.getInt("settings.screeneffect_cooldown", 20); }

    public double getBlockLagChance() { return config.getDouble("settings.blocklag_chance", 0.2); }
    public int getBlockLagCooldown() { return config.getInt("settings.blocklag_cooldown", 15); }
}
