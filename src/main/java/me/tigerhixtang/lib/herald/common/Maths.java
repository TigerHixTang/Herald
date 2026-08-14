package me.tigerhixtang.lib.herald.common;

/**
 * Small numeric helpers shared by the library.
 */
public final class Maths {

    private Maths() {
    }

    public static <T extends Comparable<T>> T clamp(T value, T min, T max) {
        if (value.compareTo(min) < 0) {
            return min;
        }
        if (value.compareTo(max) > 0) {
            return max;
        }
        return value;
    }
}
