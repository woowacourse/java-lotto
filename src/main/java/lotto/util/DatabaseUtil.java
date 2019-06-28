package lotto.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
        private static String url = "jdbc:mysql://localhost/lotto?serverTimezone=UTC";
        private static String id = "hgkim";
        private static String password = "1234";

        public static Connection getConnection() {
                try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        return DriverManager.getConnection(url, id, password);
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
                return null;
        }
}
