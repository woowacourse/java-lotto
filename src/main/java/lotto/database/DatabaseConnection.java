package lotto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SERVER = "localhost"; // MySQL 서버 주소
    private static final String DATABASE = "lottodb"; // MySQL DATABASE 이름
    private static final String USERNAME = "yk1028"; //  MySQL 서버 아이디
    private static final String PASSWORD = "!Ss7413284"; // MySQL 서버 비밀번호
    private static final String CONNECTION_URL_FORMAT =
            "jdbc:mysql://%s/%s?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

    private static Connection connection;

    private DatabaseConnection() {
        throw new AssertionError();
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            String url = String.format(CONNECTION_URL_FORMAT, SERVER, DATABASE);
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
