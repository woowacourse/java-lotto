package lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
    private static final String SERVERS = " 서버의 ";
    private static final String CONNECT_SUCCESS = " DB와 정상적으로 연결되었습니다.";
    private static final String CONNECT_FAILED = " DB와 연결하지 못했습니다: ";
    private static final String ALREADY_CLOSED = "이미 닫힌 DB 연결입니다.";
    private static final String CLOSE_FAILED = "DB와 연결을 해제하지 못했습니다: ";
    private static final String JDBC_FAILED = "JDBC 드라이버를 불러오지 못했습니다: ";

    private Connection connection;

    DBConnection(final String server, final String database, final String username, final String password) {
        connection = tryConnect(server, database, username, password);
    }

    public Connection getConnection() throws Exception {
        if (connection != null) {
            return connection;
        }
        throw new Exception(ALREADY_CLOSED);
    }

    private Connection tryConnect(final String server, final String database, final String username, final String password) {
        Connection connection = null;

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_FAILED + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            String url = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(server + SERVERS + database + CONNECT_SUCCESS);
        } catch (SQLException e) {
            System.err.println(server + SERVERS + database + CONNECT_FAILED + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }

        return connection;
    }

    // 드라이버 연결 해제
    public void closeConnection() {
        try {
            connection.close();
            this.connection = null;
        } catch (SQLException e) {
            System.err.println(CLOSE_FAILED + e.getMessage());
        }
    }
}
