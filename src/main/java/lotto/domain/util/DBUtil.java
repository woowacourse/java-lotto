package lotto.domain.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String SERVER = "localhost"; // MySQL 서버 주소
    private static final String DATABASE = "lottoDB"; // MySQL DATABASE 이름
    private static final String USER_NAME = "kangmin46"; //  MySQL 서버 아이디
    private static final String PASSWORD = "rkdals46"; // MySQL 서버 비밀번호
    public static Connection getConnection() {
        Connection con = null;
        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + "?serverTimezone=UTC" +
                    "&allowPublicKeyRetrieval=true&useSSL=false", USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
