package com.whitetiger.chestlogger.listener;

import com.whitetiger.chestlogger.manager.StorageManager;
import com.whitetiger.chestlogger.logger.LoggerManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEventListener implements Listener {
    private final JavaPlugin plugin;
    private final StorageManager storageManager;
    private final LoggerManager loggerManager;

    public BlockEventListener(JavaPlugin plugin, StorageManager storageManager, LoggerManager loggerManager) {
        this.plugin = plugin;
        this.storageManager = storageManager;
        this.loggerManager = loggerManager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Material blockMaterial = event.getBlockPlaced().getType();
        boolean isLogicBlock = storageManager.isLogicBlock(blockMaterial);
        if (storageManager.isStorageBlock(blockMaterial) || isLogicBlock) {
            String playerName = event.getPlayer().getName();
            Location location = event.getBlockPlaced().getLocation();
            plugin.getLogger().info("Logging block place: " + blockMaterial + " by " + playerName);
            loggerManager.logAction(playerName, "placed", blockMaterial, location, isLogicBlock);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockMaterial = event.getBlock().getType();
        boolean isLogicBlock = storageManager.isLogicBlock(blockMaterial);
        if (storageManager.isStorageBlock(blockMaterial) || isLogicBlock) {
            String playerName = event.getPlayer().getName();
            Location location = event.getBlock().getLocation();
            plugin.getLogger().info("Logging block break: " + blockMaterial + " by " + playerName);
            loggerManager.logAction(playerName, "broke", blockMaterial, location, isLogicBlock);
        }
    }
}
