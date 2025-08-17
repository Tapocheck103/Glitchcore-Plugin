package com.tapocheck103.glitchcorePlugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class EffectUtils {

    private static final Random random = new Random();

    /**
     * Возвращает случайного онлайн-игрока
     */
    public static Player getRandomPlayer() {
        List<? extends Player> players = Bukkit.getOnlinePlayers().stream().toList();
        if (players.isEmpty()) return null;
        return players.get(random.nextInt(players.size()));
    }

    /**
     * Накладывает эффект на случайного игрока
     */
    public static void giveRandomEffect(PotionEffectType type, int duration, int amplifier) {
        Player p = getRandomPlayer();
        if (p == null) return;
        p.addPotionEffect(new PotionEffect(type, duration, amplifier));
        p.sendMessage("§5[Аномалия] §7Вы почувствовали странное воздействие...");
    }
}
