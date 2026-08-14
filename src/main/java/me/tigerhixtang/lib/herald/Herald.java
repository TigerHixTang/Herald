package me.tigerhixtang.lib.herald;

import me.tigerhixtang.lib.herald.handler.BossbarHandler;
import me.tigerhixtang.lib.herald.handler.PaperBossbarHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point of Herald. When used as a standalone plugin, the plugin
 * instance is registered automatically. When shaded into your own plugin,
 * call {@link #setPluginInstance(Plugin)} from {@code onEnable()}.
 */
public final class Herald extends JavaPlugin {

    private static Plugin instance;
    private static BossbarHandler handler;

    private Herald() {
    }

    public static Plugin getPluginInstance() {
        return instance;
    }

    /**
     * Binds Herald to a plugin instance. The first call wins; calling this
     * method again after initialization has no effect.
     *
     * @param plugin the plugin that owns Herald
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
        Herald.handler = handler;
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
