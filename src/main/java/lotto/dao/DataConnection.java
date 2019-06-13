package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER = "localhost";
    private static final String DATABASE = "lotto";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "fantasy7";
    private static final String URL_FORMAT = "jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC";

    public static Connection getConnection() {
        loadDriver();
        String url = String.format(URL_FORMAT, SERVER, DATABASE);
        Connection con = connectDriver(url);
        return con;
    }

    private static void loadDriver() {
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connectDriver(final String url) {
        try {
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
