# Herald

A small bossbar library for Paper 1.21+. It gives every player a persistent,
per-player bossbar without spawning fake entities, touching NMS, or relying on
reflection.

## Why Herald?

- Built on the native Paper bossbar API, so it works across supported Paper
  versions with no per-version code.
- Each player gets an independent bossbar that stays visible until you clear it.
- Changes apply immediately; no repeated update calls or flicker.
- Cleans up automatically when players leave and when the plugin is disabled.
- Works both as a standalone plugin and as a shaded dependency.

## Usage

### Standalone plugin

Drag the jar into your server's `plugins` folder and restart. The bossbar API is
ready as soon as the plugin enables.

### As a dependency

Maven:

```xml
<repository>
    <id>papermc</id>
    <url>https://repo.papermc.io/repository/maven-public/</url>
</repository>

<dependency>
    <groupId>me.tigerhixtang.lib</groupId>
    <artifactId>herald</artifactId>
    <version>2.0.0</version>
    <scope>provided</scope>
</dependency>
```

Gradle:

```kotlin
repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("me.tigerhixtang.lib:herald:2.0.0")
}
```

If you shade Herald into your own plugin, register the plugin instance from
your `onEnable()`:

```java
Herald.setPluginInstance(this);
```

### API

Show a bossbar:

```java
Player player = ...;
Bossbar bossbar = Herald.getHandler().getBossbar(player);
bossbar.setMessage(ChatColor.GOLD + "Cooking...");
bossbar.setPercentage(0.75f);
```

Check whether a player has a bossbar:

```java
boolean has = Herald.getHandler().hasBossbar(player);
```

Clear it:

```java
Herald.getHandler().clearBossbar(player);
```

## Building

```bash
mvn clean package
```

or:

```bash
gradle build
```

The build targets Java 21 and Paper API 1.21.4.

## License

[GNU Lesser General Public License v3.0](LICENSE)
