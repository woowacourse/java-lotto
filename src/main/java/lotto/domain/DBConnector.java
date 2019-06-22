package lotto.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String SERVER = "localhost"; // MySQL 서버 주소
    private static final String DATABASE = "lotto_game"; // MySQL DATABASE 이름
    private static final String USER_NAME = "stopsilver"; //  MySQL 서버 아이디
    private static final String PASSWORD = "12345678"; // MySQL 서버 비밀번호

    private DBConnector () {}

    private static class DBConnectorHolder {
        private static final DBConnector instance = new DBConnector();
    }

    public static DBConnector getInstance () {
        return DBConnectorHolder.instance;
    }

    public Connection getConnection() {
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
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
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
                System.out.println("연결이 해제되었습니다.");
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
