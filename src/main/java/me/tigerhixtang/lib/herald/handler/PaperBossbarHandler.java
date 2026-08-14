package me.tigerhixtang.lib.herald.handler;

import me.tigerhixtang.lib.herald.type.Bossbar;
import me.tigerhixtang.lib.herald.type.PaperBossbar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Paper implementation backed by the native {@link org.bukkit.boss.BossBar}
 * API. No reflection, no NMS, and no fake entities are involved.
 */
public final class PaperBossbarHandler implements Listener, BossbarHandler {

    private final Plugin plugin;
    private final Map<UUID, PaperBossbar> bossbars = new ConcurrentHashMap<>();

    public PaperBossbarHandler(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public Bossbar getBossbar(Player player) {
        return bossbars.computeIfAbsent(player.getUniqueId(), id -> new PaperBossbar(plugin, player));
    }

    @Override
    public boolean hasBossbar(Player player) {
        return bossbars.containsKey(player.getUniqueId());
    }

    @Override
    public void updateBossbar(Player player) {
        PaperBossbar bossbar = bossbars.get(player.getUniqueId());
        if (bossbar != null) {
            bossbar.show(player);
        }
    }

    @Override
    public void clearBossbar(Player player) {
        PaperBossbar bossbar = bossbars.remove(player.getUniqueId());
        if (bossbar != null) {
            bossbar.destroy();
        }
    }

    @Override
    public void clearAll() {
        for (PaperBossbar bossbar : bossbars.values()) {
            bossbar.destroy();
        }
        bossbars.clear();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        clearBossbar(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent event) {
        clearBossbar(event.getPlayer());
    }
}
