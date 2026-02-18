# Easy Hammers

![Easy Hammers Logo](src/main/resources/logo.png)

**Easy Hammers** enhances your Minecraft mining experience by adding a collection of powerful hammers that mine a **3x3 area** at once. Say goodbye to tedious strip mining and hello to efficient excavation!

## Features

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
- **Configurable**:
    - `gamerule easyhammersSneakingMode`: When true (default), sneak-mining miners only 1 block (precision mode).
    - `gamerule damageHammerByBlockCount`: When true (default), durability is used for *every* block broken in the 3x3 area.

## Installation

1.  Download and install [NeoForge](https://neoforged.net/) for Minecraft 1.21.4.
2.  Clone the repository.
3.  Copy `gradle.properties.example` to `gradle.properties`.
4.  Run `./gradlew build` to setup the workspace.

## Building from Source

If you want to contribute or build the jar yourself:

1.  Clone the repo.
2.  Copy `gradle.properties.example` to `gradle.properties`.
3.  (Optional) If your system Java is not Java 21, set `org.gradle.java.home` in `gradle.properties`.
4.  Run `./gradlew build`.
5.  The jar will be in `build/libs`.

## Usage

Craft a hammer using the standard pattern (similar to a pickaxe but "heavier"):

s = stick, m = material
```
m m m
m s m
  s
```
*(Check JEI/REI for exact recipes if needed)*

- **Right Click**: Normal interaction.
- **Left Click**: Mines a 3x3 area around the target block.
- **Shift + Left Click**: Mines a single block (if `easyhammersSneakingMode` is enabled).

## License

This project is licensed under the **GPL-3.0 License**. See the `LICENSE` file for details.

## Credits

- **Author**: Yiğit Güven
- **Special Thanks**: The Minecraft community for inspiration and support.
