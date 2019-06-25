package lotto.dao;

import java.sql.*;

public class DBManager {
    private static Connection conn;

    private DBManager() {

    }

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto"; // MySQL DATABASE 이름
        String userName = "root"; //  MySQL 서버 아이디
        String password = ""; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        // 드라이버 연결
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public static int lastRound() {
        String sql = "SELECT MAX(round) FROM lotto_game";
        ResultSet rs;
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}