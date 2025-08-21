package com.tapocheck103.glitchcorePlugin;

import com.tapocheck103.glitchcorePlugin.commands.GlitchCommand;
import com.tapocheck103.glitchcorePlugin.config.ConfigManager;
import com.tapocheck103.glitchcorePlugin.events.AnomalyListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GlitchcorePlugin extends JavaPlugin {
    private static GlitchcorePlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        // Загружаем конфиг
        configManager = new ConfigManager(this);

        // Регистрируем команду
        getCommand("glitchcore").setExecutor(new GlitchCommand(this));

        // Регистрируем ивенты
        Bukkit.getPluginManager().registerEvents(new AnomalyListener(), this);

        getLogger().info("GlitchcorePlugin включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GlitchcorePlugin выключен!");
    }

    public static GlitchcorePlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
