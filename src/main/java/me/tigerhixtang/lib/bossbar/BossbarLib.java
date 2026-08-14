package me.tigerhixtang.lib.bossbar;

import me.tigerhixtang.lib.bossbar.handler.BossbarHandler;
import me.tigerhixtang.lib.bossbar.handler.PaperBossbarHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point of BossbarLib. When used as a standalone plugin, the plugin
 * instance is registered automatically. When shaded into your own plugin,
 * call {@link #setPluginInstance(Plugin)} from {@code onEnable()}.
 */
public final class BossbarLib extends JavaPlugin {

    private static Plugin instance;
    private static BossbarHandler handler;

    private BossbarLib() {
    }

    public static Plugin getPluginInstance() {
        return instance;
    }

    /**
     * Binds BossbarLib to a plugin instance. The first call wins; calling this
     * method again after initialization has no effect.
     *
     * @param plugin the plugin that owns BossbarLib
     */
    public static void setPluginInstance(Plugin plugin) {
        if (instance != null) {
            return;
        }
        instance = plugin;
        setHandler(new PaperBossbarHandler(plugin));
    }

    public static BossbarHandler getHandler() {
        return handler;
    }

    public static void setHandler(BossbarHandler handler) {
        BossbarLib.handler = handler;
    }

    @Override
    public void onEnable() {
        setPluginInstance(this);
    }

    @Override
    public void onDisable() {
        if (handler != null) {
            handler.clearAll();
        }
    }
}
