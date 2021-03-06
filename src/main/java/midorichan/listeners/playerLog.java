package midorichan.listeners;

import midorichan.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLog implements Listener {

    private final String prefix = Main.getPrefix();

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        boolean togglemsg = Main.config.getBoolean("login-message");
        String content = Main.config.getString("login-message-content");

        if (togglemsg) {
            if (content == null || content.isEmpty()) {
                e.setJoinMessage(prefix + p.getName() + " joined the game.");
            }

            assert content != null;
            e.setJoinMessage(content.replace("{PLAYER}", p.getName())
                    .replace("{PLAYERUUID}", p.getUniqueId().toString())
                    .replace("{PLAYERWORLD}", p.getWorld().getName())
                    .replace("{PREFIX}", prefix));
        }

        boolean toggletitle = Main.config.getBoolean("login-title");
        String titlemsg = Main.config.getString("login-title-content");
        String subtitlemsg = Main.config.getString("login-subtitle-content");

        if (toggletitle) {
            assert titlemsg != null;
            assert subtitlemsg != null;
            p.sendTitle(titlemsg.replace("&", "§")
                            .replace("{PLAYER}", p.getName())
                            .replace("{PLAYERUUID}", p.getUniqueId().toString())
                            .replace("{PLAYERWORLD}", p.getWorld().getName())
                            .replace("{PREFIX}", prefix),
                    subtitlemsg.replace("&", "§")
                            .replace("{PLAYER}", p.getName())
                            .replace("{PLAYERUUID}", p.getUniqueId().toString())
                            .replace("{PLAYERWORLD}", p.getWorld().getName())
                            .replace("{PREFIX}", prefix),
                    20, 20, 20);
        }

    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        boolean togglemsg = Main.config.getBoolean("logout-message");
        String content = Main.config.getString("logout-message-content");

        if (togglemsg) {
            if (content == null || content.isEmpty()) {
                e.setQuitMessage(prefix + p.getName() + " left the game.");
            }

            assert content != null;
            e.setQuitMessage(content.replace("{PLAYER}", p.getName())
                    .replace("{PLAYERUUID}", p.getUniqueId().toString())
                    .replace("{PLAYERWORLD}", p.getWorld().getName())
                    .replace("{PREFIX}", prefix));
        }

    }

}
