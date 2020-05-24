package xyz.acrylicstyle.home.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

public class HomeCommand extends PlayerCommandExecutor implements TabCompleter {
    @Override
    public void onCommand(Player player, String[] args) {
        if (!player.getWorld().getName().toLowerCase().equalsIgnoreCase("world")) {
            player.sendMessage(ChatColor.RED + "メイン(world)ワールドでないと使用できません。");
            return;
        }
        double x = Home.config.getDouble("players." + player.getUniqueId().toString() + ".x", 0);
        double y = Home.config.getDouble("players." + player.getUniqueId().toString() + ".y", -1);
        double z = Home.config.getDouble("players." + player.getUniqueId().toString() + ".z", 0);
        if (y < 0) {
            player.sendMessage(ChatColor.RED + "homeが設定されていません。/sethomeで設定してください。");
            return;
        }
        if (args.length != 0 && args[0].equalsIgnoreCase("show")) {
            player.sendMessage(ChatColor.GREEN + "現在のHomeの場所は"
                    + ChatColor.RED + x + ChatColor.GREEN + ", "
                    + ChatColor.RED + y + ChatColor.GREEN + ", "
                    + ChatColor.RED + z + ChatColor.GREEN + "です。");
            return;
        }
        Location location = new Location(Bukkit.getWorld("world"), x, y, z);
        player.setFallDistance(0);
        player.teleport(location);
        player.sendMessage(ChatColor.GREEN + "Homeにテレポートしました。");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0 || args.length == 1) return Collections.singletonList("show");
        return Collections.emptyList();
    }
}
