package me.tigerhixtang.lib.bossbar.handler;

import me.tigerhixtang.lib.bossbar.type.Bossbar;
import org.bukkit.entity.Player;

/**
 * Manages per-player bossbars.
 */
public interface BossbarHandler {

    /**
     * Returns the bossbar of a player, creating one if necessary.
     *
     * @param player target player
     * @return the player's bossbar
     */
    Bossbar getBossbar(Player player);

    /**
     * Returns whether the player already has a bossbar.
     *
     * @param player target player
     * @return whether the player has a bossbar
     */
    boolean hasBossbar(Player player);

    /**
     * Makes sure the player's bossbar is visible. Implementations update
     * automatically when a bossbar is modified, so this method is optional.
     *
     * @param player target player
     */
    void updateBossbar(Player player);

    /**
     * Removes and destroys the player's bossbar.
     *
     * @param player target player
     */
    void clearBossbar(Player player);

    /**
     * Clears every active bossbar. Called automatically on plugin disable.
     */
    default void clearAll() {
    }
}
