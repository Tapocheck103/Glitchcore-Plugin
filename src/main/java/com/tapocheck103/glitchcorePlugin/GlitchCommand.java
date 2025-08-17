package com.tapocheck103.glitchcorePlugin;

import com.tapocheck103.glitchcorePlugin.utils.EffectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class GlitchCommand implements CommandExecutor {

    private final GlitchcorePlugin plugin;

    public GlitchCommand(GlitchcorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§eИспользование: /glitch <fakenick|blocklag|toolfail|phantomdamage|screeneffect|random|reload>");
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "fakenick" -> {
                if (!(sender instanceof Player p)) {
                    sender.sendMessage("§cЭта команда только для игроков!");
                    return true;
                }
                spawnFakeNick(p);
            }
            case "blocklag" -> {
                if (!(sender instanceof Player p)) {
                    sender.sendMessage("§cЭта команда только для игроков!");
                    return true;
                }
                simulateBlockLag(p);
            }
            case "toolfail" -> {
                if (!(sender instanceof Player p)) {
                    sender.sendMessage("§cЭта команда только для игроков!");
                    return true;
                }
                simulateToolFail(p);
            }
            case "phantomdamage" -> {
                if (!(sender instanceof Player p)) {
                    sender.sendMessage("§cЭта команда только для игроков!");
                    return true;
                }
                simulatePhantomDamage(p);
            }
            case "screeneffect" -> EffectUtils.giveRandomEffect(PotionEffectType.DARKNESS, 60, 0);
            case "random" -> {
                if (!(sender instanceof Player p)) {
                    sender.sendMessage("§cЭта команда только для игроков!");
                    return true;
                }
                runRandom(p);
            }
            case "reload" -> {
                if (!sender.hasPermission("glitchcore.reload")) {
                    sender.sendMessage("§cУ вас нет прав!");
                    return true;
                }
                plugin.reloadConfig();
                plugin.getLogger().info("Config reloaded!");
                sender.sendMessage("§aКонфиг успешно перезагружен!");
            }
            default -> sender.sendMessage("§cНеизвестная подкоманда.");
        }

        return true;
    }

    private void spawnFakeNick(Player player) {
        ArmorStand stand = player.getLocation().add(2, 0, 0).getWorld().spawn(player.getLocation(), ArmorStand.class, s -> {
            s.setVisible(false);
            s.setCustomNameVisible(true);
            s.setCustomName("Player" + UUID.randomUUID().toString().substring(0, 5));
            s.setMarker(true);
        });
        Bukkit.getScheduler().runTaskLater(plugin, stand::remove, 60L);
        player.sendMessage("§aФантомный ник создан!");
    }

    private void simulateBlockLag(Player player) {
        Block block = player.getTargetBlockExact(5);
        if (block == null || block.getType() == Material.AIR) {
            player.sendMessage("§cНет блока для лага!");
            return;
        }
        Material type = block.getType();
        block.setType(Material.AIR);
        Bukkit.getScheduler().runTaskLater(plugin, () -> block.setType(type), 40L);
        player.sendMessage("§aФантомный лаг блока сработал!");
    }

    private void simulateToolFail(Player player) {
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            player.sendMessage("§cВаш " + player.getInventory().getItemInMainHand().getType().name().toLowerCase() + " внезапно отказал!");
        } else {
            player.sendMessage("§cУ вас в руке нет инструмента!");
        }
    }

    private void simulatePhantomDamage(Player player) {
        player.damage(1.0);
        player.sendMessage("§7Вы почувствовали странное воздействие...");
    }

    private void runRandom(Player player) {
        int rnd = (int) (Math.random() * 5);
        switch (rnd) {
            case 0 -> spawnFakeNick(player);
            case 1 -> simulateBlockLag(player);
            case 2 -> simulateToolFail(player);
            case 3 -> simulatePhantomDamage(player);
            case 4 -> EffectUtils.giveRandomEffect(PotionEffectType.DARKNESS, 60, 0);
        }
    }
}
