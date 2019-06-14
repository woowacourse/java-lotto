package lotto.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public Connection getConnection() {
        Connection connection = null;
        String server = "seongmo.synology.me";
        String database = "lotto";
        String userName = "techcourse";
        String password = "8IaSoMTzND7qeNuW";
        String portNumber = ":3307";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("!! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + portNumber + "/" +
                    database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }
}
