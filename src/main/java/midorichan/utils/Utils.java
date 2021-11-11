package midorichan.utils;

import midorichan.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

public class Utils {

    private static Main plugin = Main.getInstance();

    public static String replaceLast(String text, String regex, String replacement){
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static int getPing(Player player) {
        int ping = -1;
        try {
            Class<?> cp = Class.forName("org.bukkit.craftbukkit."+ plugin.getVersion() +".entity.CraftPlayer");
            Object cpc = cp.cast(player);
            Method m = cpc.getClass().getMethod("getHandle");
            Object o = m.invoke(cpc);
            Field f = o.getClass().getField("ping");
            ping = f.getInt(o);
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.getLocalizedMessage());
        }
        return ping;
    }


}
