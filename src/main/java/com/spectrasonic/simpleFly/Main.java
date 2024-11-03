package com.spectrasonic.simpleFly;

import com.spectrasonic.simpleFly.commands.FlyCommand;
import com.spectrasonic.simpleFly.commands.FlyTabCompleter;
import com.spectrasonic.simpleFly.listeners.FallDamageListener;
import com.spectrasonic.simpleFly.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the Fly Plugin.
 * Initializes commands and listeners.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();

        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    /**
     * Registers the Fly command and its TabCompleter.
     */
    private void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("fly").setTabCompleter(new FlyTabCompleter());
    }

    /**
     * Registers the FallDamageListener to prevent fall damage.
     */
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new FallDamageListener(), this);
    }

}
