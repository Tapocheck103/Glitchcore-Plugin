package com.tapocheck103.glitchcorePlugin;

import com.tapocheck103.glitchcorePlugin.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class GlitchcorePlugin extends JavaPlugin {

    private static GlitchcorePlugin instance;
    private ConfigManager configManager;
    private SchedulerManager schedulerManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        configManager = new ConfigManager(this);

        // Регистрируем слушателей
        if (configManager.isFakeNicknamesEnabled())
            getServer().getPluginManager().registerEvents(new FakeNicknameListener(configManager), this);

        if (configManager.isBlockLaggingEnabled())
            getServer().getPluginManager().registerEvents(new BlockLagListener(configManager), this);

        if (configManager.isGlitchyToolsEnabled())
            getServer().getPluginManager().registerEvents(new GlitchyToolsListener(configManager), this);

        if (configManager.isPhantomDamageEnabled())
            getServer().getPluginManager().registerEvents(new PhantomDamageListener(configManager), this);

        getServer().getPluginManager().registerEvents(new ScreenEffectListener(configManager), this);

        schedulerManager = new SchedulerManager(this, configManager);
        schedulerManager.start();

        // Регистрируем команду
        getCommand("glitch").setExecutor(new GlitchCommand(this));

        getLogger().info("Glitchcore включен!");
    }


    @Override
    public void onDisable() {
        if (schedulerManager != null) schedulerManager.stop();
        getLogger().info("Glitchcore выключен!");
    }

    public static GlitchcorePlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
