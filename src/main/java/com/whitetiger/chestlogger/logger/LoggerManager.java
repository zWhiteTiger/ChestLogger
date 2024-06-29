package com.whitetiger.chestlogger.logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoggerManager {

    private final JavaPlugin plugin;
    private final Map<String, YamlConfiguration> storagePlayerLogs;
    private final Map<String, YamlConfiguration> logicPlayerLogs;

    public LoggerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.storagePlayerLogs = new HashMap<>();
        this.logicPlayerLogs = new HashMap<>();
    }

    public void logAction(String playerName, String actionType, Material blockMaterial, Location location, boolean isLogicBlock) {
        String logMessage = String.format("%s %s %s", playerName, actionType, blockMaterial.name());
        String locationKey = String.format("location(%d,%d,%d)", location.getBlockX(), location.getBlockY(), location.getBlockZ());
        String currentDate = getCurrentDate();

        YamlConfiguration playerLog = getPlayerLog(playerName, isLogicBlock);

        if (!playerLog.contains(currentDate)) {
            playerLog.createSection(currentDate);
        }

        if (!playerLog.isConfigurationSection(currentDate)) {
            playerLog.createSection(currentDate);
        }

        if (playerLog.isConfigurationSection(currentDate)) {
            if (!playerLog.contains(currentDate + "." + locationKey)) {
                playerLog.createSection(currentDate + "." + locationKey);
            }
            int nextKey = playerLog.getConfigurationSection(currentDate + "." + locationKey).getKeys(false).size() + 1;
            playerLog.set(currentDate + "." + locationKey + "." + nextKey, logMessage);
        }

        savePlayerLog(playerName, playerLog, isLogicBlock);
    }

    private YamlConfiguration getPlayerLog(String playerName, boolean isLogicBlock) {
        String folder = isLogicBlock ? "Logic" : "Storage";
        Map<String, YamlConfiguration> playerLogs = isLogicBlock ? logicPlayerLogs : storagePlayerLogs;

        if (!playerLogs.containsKey(playerName)) {
            File playerLogFile = new File(plugin.getDataFolder() + "/" + folder, playerName + ".yml");
            if (!playerLogFile.exists()) {
                try {
                    playerLogFile.createNewFile();
                } catch (IOException e) {
                    plugin.getLogger().warning("Failed to create player log file: " + playerName + ".yml");
                }
            }
            YamlConfiguration playerLog = YamlConfiguration.loadConfiguration(playerLogFile);
            playerLogs.put(playerName, playerLog);
        }
        return playerLogs.get(playerName);
    }

    private void savePlayerLog(String playerName, YamlConfiguration playerLog, boolean isLogicBlock) {
        String folder = isLogicBlock ? "Logic" : "Storage";
        File playerLogFile = new File(plugin.getDataFolder() + "/" + folder, playerName + ".yml");
        try {
            playerLog.save(playerLogFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save player log file: " + playerName + ".yml");
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
