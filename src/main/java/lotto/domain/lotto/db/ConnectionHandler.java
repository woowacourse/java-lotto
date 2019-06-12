package lotto.domain.lotto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    private String databaseName;

    public ConnectionHandler(String databaseName) {
        this.databaseName = databaseName;
    }

    public Connection getConnection() {
        Connection con = null;
        String DB_URL = "jdbc:mysql://localhost/";
        String server = "localhost"; // MySQL 서버 주소
        String userName = "Ole"; //  MySQL 서버 아이디
        String password = "123"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + databaseName + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
