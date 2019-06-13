package lotto.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public Connection getConnection() {
        Connection connection = null;
        String server = "seongmo.synology.me"; // MySQL 서버 주소
        String database = "techcourse_lotto"; // MySQL DATABASE 이름
        String userName = "techcourse"; //  MySQL 서버 아이디
        String password = "8IaSoMTzND7qeNuW"; // MySQL 서버 비밀번호
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
            System.out.println("정상적으로 연결되었습니다~");
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류: " + e.getMessage());
        }
    }
}
