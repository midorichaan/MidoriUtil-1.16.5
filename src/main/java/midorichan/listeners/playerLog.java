package midorichan.listeners;

import midorichan.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLog implements Listener {

    private static Main plugin = Main.getInstance();
    private String prefix = plugin.getPrefix();

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Boolean togglemsg = plugin.config.getBoolean("login-message");
        String content = plugin.config.getString("login-message-content");

        if (togglemsg) {
            if (content.isEmpty() || content == null) {
                content = prefix + p.getName() + " joined the game.";
            }

            e.setJoinMessage(content);
        }

        Boolean toggletitle = plugin.config.getBoolean("login-title");
        String titlemsg = plugin.config.getString("login-title-content");
        String subtitlemsg = plugin.config.getString("login-subtitle-content");

        if (toggletitle) {
            p.sendTitle(titlemsg.replace("&", "§"), subtitlemsg.replace("&", "§"), 20, 20, 20);
        }

    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        Boolean togglemsg = plugin.config.getBoolean("logout-message");
        String content = plugin.config.getString("logout-message-content");

        if (togglemsg) {
            if (content.isEmpty() || content == null) {
                content = prefix + p.getName() + " left the game.";
            }

            e.setQuitMessage(content);
        }

    }

}
