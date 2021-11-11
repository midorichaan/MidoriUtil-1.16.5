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

        Boolean toggle = plugin.getConfig().getBoolean("login-message");
        String content = plugin.getConfig().getString("login-message-content");

        if (toggle == false) {
            return;
        }

        if (content.isEmpty() || content == null) {
            content = prefix + p.getName() + " joined the game.";
        }

        e.setJoinMessage(content);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        Boolean toggle = plugin.getConfig().getBoolean("logout-message");
        String content = plugin.getConfig().getString("logout-message-content");

        if (toggle == false) {
            return;
        }

        if (content.isEmpty() || content == null) {
            content = prefix + p.getName() + " left the game.";
        }

        e.setQuitMessage(content);
    }

}
