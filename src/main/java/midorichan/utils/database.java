package midorichan.utils;

import midorichan.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    private static String address = null;
    private static String sql = null;
    private static String db = null;
    private static Integer port = null;
    private static String user = null;
    private static String passwd = null;
    private static String url = String.format(
            "jdbc:%s://%s:%s/%s",
            sql, address, port, db,
            "?autoReconnect=true&characterEncoding=UTF-8&connectionCollation=utf8mb4_general_ci"
    );

    public static void init(String addr, String dbtype, String d, Integer p, String username, String pass) {
        address = addr;
        sql = dbtype;
        db = d;
        port = p;
        user = username;
        passwd = pass;
    }

    public static Connection getConnect() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException | ClassNotFoundException e) {
            Main.instance.getLogger().info("DB Connection Exception: " + e);
        }

        return con;
    }



}
