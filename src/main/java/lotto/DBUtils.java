package lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static Connection getConnection() {
        Connection con;
        String server = "localhost"; // MySQL 서버 주소
        String database = "ike_TechCourseDB"; // MySQL DATABASE 이름
        String userName = "ike"; //  MySQL 서버 아이디
        String password = "754813as!@"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }
}
