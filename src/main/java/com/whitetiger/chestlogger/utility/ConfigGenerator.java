package com.whitetiger.chestlogger.utility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ConfigGenerator {
    private final JavaPlugin plugin;

    public ConfigGenerator(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void generateDefaultConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        // Add default messages prefix if not already set
        if (!config.isSet("Messages.prefix")) {
            config.set("Messages.prefix", "[ChestLogger]");
        }

        // Add default whitelist values if not already set
        if (!config.isSet("whitelist.block")) {
            config.set("whitelist.block", getDefaultBlockWhitelist());
        }
        if (!config.isSet("whitelist.logicBlock")) {
            config.set("whitelist.logicBlock", getDefaultLogicBlockWhitelist());
        }

        // Add default blacklist values if not already set
        if (!config.isSet("blacklist.item")) {
            config.set("blacklist.item", getDefaultBlacklist());
        }

        // Save config.yml to plugin/ChestLogger/
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config.yml", e);
        }
    }

    private List<String> getDefaultBlockWhitelist() {
        return List.of(
                "chest", "shulker_box", "red_shulker_box", "lime_shulker_box",
                "pink_shulker_box", "gray_shulker_box", "cyan_shulker_box",
                "blue_shulker_box", "white_shulker_box", "brown_shulker_box",
                "green_shulker_box", "black_shulker_box", "orange_shulker_box",
                "magenta_shulker_box", "yellow_shulker_box", "purple_shulker_box",
                "light_blue_shulker_box", "light_gray_shulker_box", "trapped_chest", "barrel"
        );
    }

    private List<String> getDefaultLogicBlockWhitelist() {
        return List.of("hopper", "hopper_minecart");
    }

    private List<String> getDefaultBlacklist() {
        return List.of("air");
    }
}
