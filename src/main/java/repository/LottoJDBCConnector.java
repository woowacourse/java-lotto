package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LottoJDBCConnector {
    private static final String server = "localhost";
    private static final String database = "lotto";
    private static final String userName = "root";
    private static final String password = "psw4Woowacourse";

    public static Connection getConnection() {
        Connection connection = null;

        loadDriver();

        try {
            connection = DriverManager.getConnection( "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 :" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("connection 오류 : " + e.getMessage());
        }
    }
}
