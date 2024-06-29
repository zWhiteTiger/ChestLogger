# ChestLogger

---

Plugin: ChestLogger

Developed By: zWhiteTiger_

Version: 1.0-SNAPSHOT

Support: 1.20 (Based on Spigot-1.20.4)

Tested On: Paper-1.20.1-170

---

## Overview

ChestLogger is a Minecraft plugin designed for SMP servers to track storage blocks such as chests, trapped chests, barrels, and shulker boxes. It logs actions such as item additions, removals, and block interactions to help server admins monitor and manage storage integrity.

## Features

- Tracks placement and destruction of storage blocks.
- Logs interactions including item additions and removals.
- Supports whitelist functionality to monitor specific blocks.
- Logs are stored in player-specific YAML files for easy auditing.

## To-Do List

This project is not final; it is currently in the development stage.

- ~~Tracking "Place event Storage"~~
- ~~Tracking "Destory event Hopper"~~
- ~~Export log to YML files Function~~
- Tracking "event edit chest"
- Search function
- etc.

## Installation

1. Download the latest version of ChestLogger from [GitHub Releases](https://github.com/zWhiteTiger/ChestLogger/releases).
2. Place the `ChestLogger.jar` file into your server's `plugins` folder.
3. Restart your server to load the plugin.

## Configuration

ChestLogger uses a `config.yml` file located in the plugin's folder for configuration. You can customize whitelist settings, logging formats, and more.

Example `config.yml`:

```yml
# ---------------------------------------------------
#                    About
# ---------------------------------------------------
Plugin: ChestLogger
Developed By: zWhiteTiger_
Version: 1.0-SNAPSHOT
Support: 1.20 (Based on Spigot-1.20.4)
Tested On: paper-1.20.1-170

# ---------------------------------------------------
#                     Messages
# ---------------------------------------------------
Messages:
  # -------------------------------------------------
  #                     Prefix
  #        Shows a prefix in front of messages
  # -------------------------------------------------

  prefix: '[ChestLogger]'

# ---------------------------------------------------
#                     Whitelist
#      This whitelist allows blocks to be tracked
# ---------------------------------------------------

whitelist:
  # -------------------------------------------------
  #                      Block
  #          Tracks actions on these blocks
  # -------------------------------------------------

  block:
    - chest
    - shulker_box
    - red_shulker_box
    - lime_shulker_box
    - pink_shulker_box
    - gray_shulker_box
    - cyan_shulker_box
    - blue_shulker_box
    - white_shulker_box
    - brown_shulker_box
    - green_shulker_box
    - black_shulker_box
    - orange_shulker_box
    - magenta_shulker_box
    - yellow_shulker_box
    - purple_shulker_box
    - light_blue_shulker_box
    - light_gray_shulker_box
    - trapped_chest
    - barrel

  # -------------------------------------------------
  #                    LogicBlock
  #        Tracks actions on transport blocks
  # -------------------------------------------------

  logicBlock:
    - hopper
    - hopper_minecart

# ---------------------------------------------------
#                     Blacklist
#     Items in this blacklist will not be tracked
# ---------------------------------------------------

blacklist:
  # -------------------------------------------------
  #                      Item
  #      Blacklists specific items from tracking
  # -------------------------------------------------

  item:
    - air  # Default value 'air' will not be tracked
