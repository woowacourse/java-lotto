package lottoGame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LottoJDBCDriverConnector {
    private static final String SERVER = "localhost"; // MySQL 서버 주소
    private static final String DATABASE = "eunsukko_db"; // MySQL DATABASE 이름
    private static final String USER_NAME = "aaaa"; //  MySQL 서버 아이디
    private static final String PASSWORD = "aaaa"; // MySQL 서버 비밀번호

    public static Connection getConnection() {
        loadJDBCDriver();
        return connect();
    }

    private static void loadJDBCDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + "?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }
}

