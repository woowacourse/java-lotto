package lotto.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

        public static Connection getConnection() {
                String url = "jdbc:mysql://localhost/lotto?serverTimezone=UTC";
                String id = "hgkim";
                String password = "1234";

                try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        return DriverManager.getConnection(url, id, password);
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                        return null;
                }
        }
}
