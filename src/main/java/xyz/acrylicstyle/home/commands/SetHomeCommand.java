package xyz.acrylicstyle.home.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.acrylicstyle.home.Home;
import xyz.acrylicstyle.tomeito_api.command.PlayerCommandExecutor;

import java.util.Collections;
import java.util.List;

public class SetHomeCommand extends PlayerCommandExecutor implements TabCompleter {
    @Override
    public void onCommand(Player player, String[] args) {
        if (!player.getWorld().getName().toLowerCase().equalsIgnoreCase("world")) {
            player.sendMessage(ChatColor.RED + "メイン(world)ワールドでないと使用できません。");
            return;
        }
        if (player.getLocation().getY() < 0) {
            player.sendMessage(ChatColor.RED + "奈落では使用できません。");
            return;
        }
        Home.config.set("players." + player.getUniqueId().toString() + ".x", player.getLocation().getX());
        Home.config.set("players." + player.getUniqueId().toString() + ".y", player.getLocation().getY());
        Home.config.setThenSave("players." + player.getUniqueId().toString() + ".z", player.getLocation().getZ());
        player.sendMessage(ChatColor.GREEN + "Homeを設定しました。");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return Collections.emptyList();
    }
}
