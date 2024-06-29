package com.whitetiger.chestlogger;

import com.whitetiger.chestlogger.listener.BlockEventListener;
import com.whitetiger.chestlogger.manager.StorageManager;
import com.whitetiger.chestlogger.logger.LoggerManager;
import com.whitetiger.chestlogger.utility.ConfigGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestLogger extends JavaPlugin {
    private StorageManager storageManager;
    private LoggerManager loggerManager;

    @Override
    public void onEnable() {
        getLogger().info("ChestLogger has been enabled!");

        // Ensure config.yaml is generated if it doesn't exist
        saveDefaultConfig();
        new ConfigGenerator(this).generateDefaultConfig();

        // Initialize StorageManager and LoggerManager
        storageManager = new StorageManager(this);
        loggerManager = new LoggerManager(this);

        // Register event listener
        getServer().getPluginManager().registerEvents(new BlockEventListener(this, storageManager, loggerManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("ChestLogger has been disabled!");
    }
}
