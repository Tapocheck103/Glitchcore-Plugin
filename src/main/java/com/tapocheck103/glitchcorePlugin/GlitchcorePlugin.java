package com.tapocheck103.glitchcorePlugin;

import com.tapocheck103.glitchcorePlugin.commands.GlitchCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GlitchcorePlugin extends JavaPlugin {

    private static GlitchcorePlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        // Регистрируем команду
        getCommand("glitchcore").setExecutor(new GlitchCommand(this));
        getCommand("glitchcore").setTabCompleter(new GlitchCommand(this));

        getLogger().info("Glitchcore включён!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Glitchcore выключен!");
    }

    public static GlitchcorePlugin getInstance() {
        return instance;
    }
}
