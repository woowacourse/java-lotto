package lotto.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static Connection connection = null;

    private DbConnector() {
        try {
            Properties properties = loadProperty();
            checkLoadDriver(properties);
            String url = String.format(URL_FORMAT,
                    properties.getProperty("host"),
                    properties.getProperty("port"),
                    properties.getProperty("database"));
            connection = DriverManager.getConnection(url, properties.getProperty("user"), properties.getProperty("password"));
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException | SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DbConnector();
        }
        return connection;
    }

    // 드라이버 연결해제
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public static boolean isClosed() {
        return connection == null;
    }

    private Properties loadProperty() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    private void checkLoadDriver(Properties properties) throws ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
    }
}
