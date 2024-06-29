package com.whitetiger.chestlogger.manager;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StorageManager {
    private final JavaPlugin plugin;
    private final Set<Material> storageBlocks;
    private final Set<Material> logicBlocks;

    public StorageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.storageBlocks = new HashSet<>();
        this.logicBlocks = new HashSet<>();
        loadConfiguration();
    }

    private void loadConfiguration() {
        FileConfiguration config = plugin.getConfig();

        List<String> storageBlockList = config.getStringList("whitelist.block");
        for (String blockName : storageBlockList) {
            storageBlocks.add(Material.matchMaterial(blockName.toUpperCase()));
        }

        List<String> logicBlockList = config.getStringList("whitelist.logicBlock");
        for (String blockName : logicBlockList) {
            logicBlocks.add(Material.matchMaterial(blockName.toUpperCase()));
        }
    }

    public boolean isStorageBlock(Material material) {
        return storageBlocks.contains(material);
    }

    public boolean isLogicBlock(Material material) {
        return logicBlocks.contains(material);
    }
}
