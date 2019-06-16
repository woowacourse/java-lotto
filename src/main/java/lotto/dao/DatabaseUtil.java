package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private Connection connection;

    private static class Holder {
        private static final DatabaseUtil INSTANCE = new DatabaseUtil();
    }

    static DatabaseUtil getInstance() {
        return Holder.INSTANCE;
    }

    Connection getConnection() {
        if (connection == null) {
            connection = connectDB();
        }
        return connection;
    }

    private Connection connectDB() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto"; // MySQL DATABASE 이름
        String userName = "zino"; //  MySQL 서버 아이디
        String password = "1234"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
