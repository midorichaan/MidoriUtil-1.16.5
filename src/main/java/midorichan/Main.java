package midorichan;

import midorichan.commands.fly;
import midorichan.commands.hat;
import midorichan.commands.reloadconfig;
import midorichan.commands.whois;
import midorichan.listeners.commandLog;
import midorichan.listeners.playerLog;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static String prefix;
    public static String version;
    public static FileConfiguration config = null;

    public static Main getInstance() { return instance; }
    public static String getPrefix() {
        return prefix;
    }
    public static String getVersion() {
        return version;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();
        prefix = config.getString("chat-prefix", " §2>§a>§r ");
        version = Bukkit.getServer().getClass().getPackage().getName();
        version = version.substring(version.lastIndexOf(".") + 1);

        //register listener
        Bukkit.getPluginManager().registerEvents(new commandLog(), this);
        Bukkit.getPluginManager().registerEvents(new playerLog(), this);
        //register commands
        Bukkit.getPluginCommand("reloadconfig").setExecutor(new reloadconfig());
        Bukkit.getPluginCommand("whois").setExecutor(new whois());
        Bukkit.getPluginCommand("hat").setExecutor(new hat());
        Bukkit.getPluginCommand("fly").setExecutor(new fly());

        getLogger().info(getPrefix() + "Enabled MidoriUtil for 1.16.5");
    }

    @Override
    public void onDisable() {
        getLogger().info(getPrefix() + "Disabled MidoriUtil for 1.16.5");
    }

}
