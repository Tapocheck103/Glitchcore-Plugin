package com.tapocheck103.glitchcorePlugin.commands;

import com.tapocheck103.glitchcorePlugin.GlitchcorePlugin;
import com.tapocheck103.glitchcorePlugin.anomalies.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GlitchCommand implements CommandExecutor, TabCompleter {

    private final GlitchcorePlugin plugin;

    public GlitchCommand(GlitchcorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§cИспользование: /glitchcore <аномалия> [...]");
            return true;
        }

        String anomaly = args[0].toLowerCase();

        switch (anomaly) {
            case "fakenick" -> {
                if (args.length < 3) {
                    sender.sendMessage("§cИспользование: /glitchcore fakenick <фейковый_ник> <игрок>");
                    return true;
                }
                String fakeName = args[1];
                Player target = Bukkit.getPlayer(args[2]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new FakeNickAnomaly().trigger(target, fakeName);
            }
            case "fakechat" -> {
                if (args.length < 3) {
                    sender.sendMessage("§cИспользование: /glitchcore fakechat <фейковый_ник> <сообщение>");
                    return true;
                }

                String fakeName = args[1];
                // Собираем сообщение начиная с 3-го аргумента
                StringBuilder msgBuilder = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    msgBuilder.append(args[i]).append(" ");
                }
                String message = msgBuilder.toString().trim();

                new FakeChatMessageAnomaly().trigger(fakeName, message);
            }
            case "compass" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore compass <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new CompassGlitchAnomaly().trigger(target);
            }
            case "phantomdamage" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore phantomdamage <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new PhantomDamageAnomaly().trigger(target);
            }
            case "torchfade" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore torchfade <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new TorchFadeAnomaly().trigger(target);
            }
            case "teleport" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore teleport <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new RandomTeleportAnomaly().trigger(target);
            }
            case "sound" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore sound <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new SoundGlitchAnomaly().trigger(target);
            }
            case "particles" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cИспользование: /glitchcore particles <игрок>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                new ParticleGlitchAnomaly().trigger(target);
            }
            default -> sender.sendMessage("§cНеизвестная аномалия!");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("fakenick", "fakechat", "compass", "phantomdamage", "torchfade", "teleport", "sound", "particles");
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("fakenick")) {
            return Collections.singletonList("<фейковый_ник>");
        }
        if (args.length == 2) {
            return null; // автодополнение игроков
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("fakenick")) {
            return null; // автодополнение игроков
        }
        return Collections.emptyList();
    }
}
