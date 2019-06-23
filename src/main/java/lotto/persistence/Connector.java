package lotto.persistence;

import lotto.persistence.exceptions.FailedDBConnectionException;
import lotto.persistence.exceptions.FailedJDBCDriverLoadingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "JAVA_LOTTO";
        String userName = "yumin";
        String password = "1234";

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println((" !! JDBC Driver load 오류: " + e.getMessage()));
            e.printStackTrace();
            throw new FailedJDBCDriverLoadingException("JDBC 드라이버 연결을 로드하는 데 실패하였습니다.");
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/"
                    + database + "?serverTimezone=UTC&useSSL=false", userName, password);
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 오류: " + e.getMessage());
            e.printStackTrace();
            throw new FailedDBConnectionException("데이터베이스 연결에 실패하였습니다.");
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
