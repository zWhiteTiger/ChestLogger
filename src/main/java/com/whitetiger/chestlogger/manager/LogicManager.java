package com.whitetiger.chestlogger.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class LogicManager {
    private JavaPlugin plugin;
    private List<String> logicBlocks;

    public LogicManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadWhitelist();
    }

    private void loadWhitelist() {
        FileConfiguration config = plugin.getConfig();
        logicBlocks = config.getStringList("whitelist.logicBlock");
    }

    public boolean isLogicBlock(String blockType) {
        return logicBlocks.contains(blockType);
    }
}