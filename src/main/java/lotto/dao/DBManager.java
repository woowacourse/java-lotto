package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost:3307"; // MySQL 서버 주소
        String database = "lotto_game"; // MySQL DATABASE 이름
        String userName = "codemcd"; //  MySQL 서버 아이디
        String password = "0803"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            StringBuilder connectionInfo = new StringBuilder();
            con = DriverManager.getConnection(connectionInfo
                            .append("jdbc:mysql://")
                            .append(server)
                            .append("/")
                            .append(database)
                            .append("?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false").toString(),
                    userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public static void startTransaction(Connection con) throws SQLException {
        con.setAutoCommit(false);
    }

    public static void endTransaction(Connection con) throws SQLException {
        con.commit();
    }

    public static void rollbackTransaction(Connection connection) throws SQLException {
        connection.rollback();
    }
}
