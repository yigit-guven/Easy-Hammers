![Easy Hammers Logo](/src/main/resources/logo.png)

[![CurseForge](https://img.shields.io/badge/CurseForge-Available-orange?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/easy-hammers)
[![Modrinth](https://img.shields.io/badge/Modrinth-Available-green?style=for-the-badge&logo=modrinth)](https://modrinth.com/mod/easy-hammers)
[![Discord](https://img.shields.io/badge/Discord-Join%20Us-7289DA?style=for-the-badge&logo=discord)](https://discord.gg/gNajXYku5z)
[![NeoForge 1.21.1](https://img.shields.io/badge/NeoForge-1.21.1-31322C?style=for-the-badge&logo=neoforge)](https://neoforged.net/)
[![License](https://img.shields.io/badge/License-GPL%203.0-blue?style=for-the-badge)](LICENSE)

**Easy Hammers** enhances your Minecraft mining experience by adding a collection of powerful hammers that mine a **3x3 area** at once. Say goodbye to tedious strip mining and hello to efficient excavation!

> [!NOTE]
> **Version 7.0.1+** is now available for **Minecraft 1.21.1** on NeoForge!

---

## 🔗 Links

| Platform | Link |
| :--- | :--- |
| **📥 CurseForge** | [Download Here](https://www.curseforge.com/minecraft/mc-mods/easy-hammers) |
| **📥 Modrinth** | [Download Here](https://modrinth.com/mod/easy-hammers) |
| **💬 Discord** | [Join Community](https://discord.gg/gNajXYku5z) |
| **🐛 Issues** | [Report Bugs](https://github.com/yigit-guven/Easy-Hammers/issues/) |
| **📚 Wiki** | [Documentation](https://github.com/yigit-guven/Easy-Hammers/wiki) |
| **💻 Source** | [GitHub Repo](https://github.com/yigit-guven/Easy-Hammers) |
| **👤 Author** | [Yiğit Güven](https://github.com/yigit-guven) \| [Website](https://yigitguven.net/) |

---

## ✨ Features

- **3x3 Mining**: Break 9 blocks with a single swing! Perfect for strip mining and large-scale excavation.
- **Vanilla-Friendly Tiers**: Progression mirrors vanilla tools, from Wooden to Netherite.
    - **Wooden Hammer**: Early game clearing.
    - **Stone Hammer**: Solid starter option.
    - **Iron Hammer**: Reliable workhorse.
    - **Golden Hammer**: Fast but fragile.
    - **Diamond Hammer**: High durability and speed.
    - **Netherite Hammer**: The ultimate mining tool.
- **Balanced Gameplay**: Hammers are "Heavy" tools. They swing slower than pickaxes but pack a punch with higher damage and ~4x durability to compensate for the area effect.
- **Enchantments**:
    - **Soil Breaker**: Allows the hammer to also mine dirt, sand, gravel, and grass in the 3x3 area (normally only mines stone-like blocks).
    - **Smash**: Chance to stun enemies for a short duration on critical hits, slowing them and reducing their damage.
- **Configurable**:
    - `gamerule easyhammersSneakingMode`: When true (default), sneak-mining mines only 1 block (precision mode).
    - `gamerule damageHammerByBlockCount`: When true (default), durability is used for *every* block broken in the 3x3 area.

## 🛠️ Crafting

Craft a hammer using the standard pattern (similar to a pickaxe but "heavier"):

**Recipe**:
```
M M M
M S M
  S
```
*(M = Material, S = Stick)*

## 📦 Installation

1.  Download and install Modloader for Minecraft.
2.  Download the **Easy Hammers** mod jar from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/easy-hammers) or [Modrinth](https://modrinth.com/mod/easy-hammers).
3.  Place the jar file in your `.minecraft/mods` folder.
4.  Launch Minecraft and enjoy!

## 👨‍💻 Building from Source

If you want to contribute or build the jar yourself:

1.  Clone the repository.
    ```bash
    git clone https://github.com/yigit-guven/Easy-Hammers.git
    ```
2.  Copy `gradle.properties.example` to `gradle.properties`.
3.  (Optional) If your system Java is not Java 21, set `org.gradle.java.home` in `gradle.properties`.
4.  Run `./gradlew build`.
5.  The jar will be in `build/libs`.

## 📜 License

This project is licensed under the **GPL-3.0 License**. See the `LICENSE` file for details.

## ❤️ Credits

- **Author**: [Yiğit Güven](https://yigitguven.net/)
- **Special Thanks**: The Minecraft community for inspiration and support.
