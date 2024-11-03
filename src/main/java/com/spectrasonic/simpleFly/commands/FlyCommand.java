package com.spectrasonic.simpleFly.commands;

import com.spectrasonic.simpleFly.Main;
import com.spectrasonic.simpleFly.utils.MessageUtils;
//import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//@RequiredArgsConstructor
public class FlyCommand implements CommandExecutor {

    private final Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        // Permitir que todos los jugadores usen "/fly version"
        if (args.length > 0 && args[0].equalsIgnoreCase("version")) {
            MessageUtils.sendMessage(sender, "Version: &b%s", plugin.getDescription().getVersion());
            MessageUtils.sendMessage(sender, "Developed by: &c%s", plugin.getDescription().getAuthors());
            return true;
        }

        // Verificar si el remitente es un operador
        if (!sender.isOp()) {
            MessageUtils.sendMessage(sender, "&cYou don't have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            if (sender instanceof Player) {
                toggleFly((Player) sender);
            } else {
                MessageUtils.sendMessage(sender, "&cThis command can only be used by players.");
            }
        } else {
            if (args[0].equalsIgnoreCase("all")) {
                toggleFlyForAll(sender);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    toggleFly(target);
                } else {
                    MessageUtils.sendMessage(sender, "&cPlayer %s is not online.", args[0]);
                }
            }
        }
        return true;
    }

    private void toggleFly(Player player) {
        boolean canFly = !player.getAllowFlight();
        player.setAllowFlight(canFly);
        MessageUtils.sendMessage(player, "Fly mode is %s.", canFly ? "&aON" : "&cOFF");
    }

    private void toggleFlyForAll(CommandSender sender) {
        Bukkit.getOnlinePlayers().forEach(this::toggleFly);
        MessageUtils.sendMessage(sender, "Fly mode has been changed for all players.");
    }
}
