package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WinningLottoDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto_db"; // MySQL DATABASE 이름
        String userName = "user"; //  MySQL 서버 아이디
        String password = "gmlgus12"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
