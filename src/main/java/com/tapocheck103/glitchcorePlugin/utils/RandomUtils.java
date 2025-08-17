package com.tapocheck103.glitchcorePlugin.utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    /**
     * Случайное число от 0 до 1 (double)
     */
    public static double nextDouble() {
        return random.nextDouble();
    }

    /**
     * Случайное целое число в диапазоне [min; max]
     */
    public static int nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Возвращает true с указанной вероятностью (0.0 - никогда, 1.0 - всегда)
     */
    public static boolean chance(double probability) {
        return random.nextDouble() < probability;
    }

    /**
     * Выбор случайного элемента из списка
     */
    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(random.nextInt(list.size()));
    }
}
