# TabPac 🏷️

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Paper-aqua?style=flat-square)
![Telegram](https://img.shields.io/badge/Telegram-@mybiopac-blue?logo=telegram&style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**TabPac** is a modern, lightweight, and highly customizable Minecraft plugin for Paper/Spigot servers (1.21.x) that manages your Tablist and Sidebar Scoreboard with style. It's designed to be a "branded" alternative to popular tab plugins, featuring built-in animation support, multi-language capabilities, and deep integration with LuckPerms and PlaceholderAPI.

## ✨ Features

*   **Dynamic Tablist**: Fully customizable header and footer with multi-line support.
*   **Sidebar Scoreboard**: A sleek sidebar to display player stats, server info, and more.
*   **Animation System**: Create smooth, frame-based animations for any text in the Tab or Scoreboard.
*   **Smart Sorting**: Sort players in the Tablist based on a custom priority list (e.g., Admin > Moderator > Player).
*   **AFK Support**: Built-in integration with EssentialsX to show AFK status in the Tablist.
*   **Multi-Language**: Native support for **English**, **Russian**, **Ukrainian**, and **Belarusian**.
*   **PlaceholderAPI Integration**: Support for thousands of placeholders for dynamic data.
*   **Smart Error Detection**: Notifies admins if required PAPI expansions are missing or if there are configuration errors.

## 🚀 Commands

*   `/tabpac` - Main command, shows version info.
*   `/tabpac reload` - Reloads the configuration and language files.
*   `/tabpac papi download` - Automatically downloads required PAPI expansions (Player, Server, LuckPerms).

## 🔑 Permissions

*   `tabpac.use` - Access to the main command (default: true).
*   `tabpac.reload` - Permission to reload the plugin (default: op).
*   `tabpac.admin` - Receive admin notifications about PAPI or config errors.

## 🛠️ Configuration

The `config.yml` is simple and powerful:

```yaml
language: ru # en, ru, uk, be

groups-priority:
  - admin
  - default

tablist:
  header:
    - "%animation:logo%"
    - "&7Welcome, &b%player_name%&7!"
  footer:
    - "&7Join our Telegram: &b@mybiopac"

animations:
  logo:
    update-interval-ticks: 5
    frames:
      - "&b&lT&f&labPac"
      - "&f&lT&b&la&f&lbPac"
```

## 📦 Installation

1.  Download the latest `TabPac.jar`.
2.  Place it in your server's `plugins` folder.
3.  Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) and [LuckPerms](https://luckperms.net/).
4.  Restart the server.
5.  Run `/tabpac papi download` to set up required placeholders.

---
Developed with ❤️ by **PacHub228**
Telegram: [@mybiopac](https://t.me/mybiopac)
