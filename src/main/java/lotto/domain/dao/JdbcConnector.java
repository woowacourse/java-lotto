package lotto.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnector {

    private JdbcConnector() {
        throw new AssertionError();
    }

    // TODO: 2019-06-12 Do clean & divide static method!!
    public static Connection getConnection() {
        Connection connection = null;
        String server = "localhost";
        String database = "lotto";
        String userName = "whale";
        String password = "whale";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("!! JDBC DRIVER load error : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (Exception e) {
            System.err.println("연결 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
