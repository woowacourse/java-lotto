package lotto.dao;
import java.sql.*;
import java.util.Optional;

public class DBManager {
    public static Connection getConnection() {
        Connection conn = null;
        String server = "localhost";
        String database = "lotto_db";
        String userName = "yuyu154";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database
                    + "?useSSL=false&serverTimezone=UTC", userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
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
