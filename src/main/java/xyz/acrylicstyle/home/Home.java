package xyz.acrylicstyle.home;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.home.commands.HomeCommand;
import xyz.acrylicstyle.home.commands.SetHomeCommand;
import xyz.acrylicstyle.tomeito_api.TomeitoAPI;
import xyz.acrylicstyle.tomeito_api.providers.ConfigProvider;

public class Home extends JavaPlugin {
    public static ConfigProvider config = null;

    @Override
    public void onEnable() {
        config = ConfigProvider.getConfig("./plugins/Home/config.yml");
        TomeitoAPI.registerCommand("home", new HomeCommand());
        TomeitoAPI.registerCommand("sethome", new SetHomeCommand());
    }

    @Override
    public void onDisable() {
        config.save();
    }
}
