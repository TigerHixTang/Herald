package me.tigerhixtang.lib.herald.type;

/**
 * A bossbar attached to a player.
 */
public interface Bossbar {

    /**
     * Returns the current message.
     *
     * @return message
     */
    String getMessage();

    /**
     * Sets the message.
     *
     * @param message message
     * @return this
     */
    Bossbar setMessage(String message);

    /**
     * Returns the progress in the range [0, 1].
     *
     * @return progress percentage
     */
    float getPercentage();

    /**
     * Sets the progress in the range [0, 1]. Values outside the range are
     * clamped.
     *
     * @param percentage progress percentage
     * @return this
     */
    Bossbar setPercentage(float percentage);
}
