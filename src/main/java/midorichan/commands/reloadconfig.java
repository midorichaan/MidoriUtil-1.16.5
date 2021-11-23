package midorichan.commands;

import midorichan.Main;
import midorichan.utils.database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reloadconfig implements CommandExecutor {

    private final String prefix = Main.getPrefix();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("midoriutil.commands.reloadconfig")) {
            if (cmd.getName().equalsIgnoreCase("reloadconfig")) {
                if (args.length == 0) {
                    Main.instance.reloadConfig();
                    Main.config = Main.getInstance().getConfig();
                    database.init(
                            Main.config.getString("address"),
                            Main.config.getString("sql"),
                            Main.config.getString("database"),
                            Main.config.getInt("port"),
                            Main.config.getString("username"),
                            Main.config.getString("password")
                    );

                    sender.sendMessage(prefix + "Configを再読み込みしました。");
                    return true;
                } else {
                    sender.sendMessage(prefix + "引数が不正です。");
                    return true;
                }
            }
        } else {
            sender.sendMessage(prefix + "権限がありません。");
            return true;
        }

        return true;
    }

}