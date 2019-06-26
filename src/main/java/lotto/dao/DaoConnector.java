package lotto.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoConnector {
    public static Connection getConnection() {
        Properties properties = getProperties();
        Connection con = null;
        String url = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        // 드라이버 로딩
        loadDriver();

        // 드라이버 연결
        con = connectDriver(con, url, userName, password);

        return con;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("src/main/resources/database.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Connection connectDriver(Connection con, String url, String userName, String password) {
        try {
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    // 드라이버 연결해제
    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }


}
