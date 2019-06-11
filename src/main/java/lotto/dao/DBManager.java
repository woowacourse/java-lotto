package lotto.dao;
import java.sql.*;
import java.util.Optional;

public class DBManager {
    public static Connection getConnection() {
        Connection conn = null;
        PropertiesManager propManager = new PropertiesManager();
        loadClass();

        try {
            conn = DriverManager.getConnection(propManager.getUrl() + propManager.getDbName()
                    + "?useSSL=false&serverTimezone=UTC", propManager.getUsername(), propManager.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void loadClass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn) {
        Optional<Connection> optConn = Optional.ofNullable(conn);
        optConn.ifPresent((temp) -> {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("con 오류:" + e.getMessage());
            }
        });
    }
}
