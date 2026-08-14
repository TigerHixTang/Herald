package me.tigerhixtang.lib.herald.type;

import me.tigerhixtang.lib.herald.common.Maths;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * A bossbar backed by the native Paper bossbar API.
 */
public final class PaperBossbar implements Bossbar {

    private final BossBar handle;
    private final Player player;

    public PaperBossbar(Plugin plugin, Player player) {
        this.player = player;
        NamespacedKey key = new NamespacedKey(plugin, "bossbar-" + player.getUniqueId());
        this.handle = Bukkit.createBossBar(key, "", BarColor.PURPLE, BarStyle.SOLID);
        show(player);
    }

    @Override
    public String getMessage() {
        return handle.getTitle();
    }

    @Override
    public Bossbar setMessage(String message) {
        handle.setTitle(message);
        return this;
    }

    @Override
    public float getPercentage() {
        return (float) handle.getProgress();
    }

    @Override
    public Bossbar setPercentage(float percentage) {
        handle.setProgress(Maths.clamp(percentage, 0f, 1f));
        return this;
    }

    public void show(Player player) {
        handle.addPlayer(player);
        handle.setVisible(true);
    }

    public void destroy() {
        handle.removePlayer(player);
    }
}
