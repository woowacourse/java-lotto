package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LottoDAOConnector {

    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "lotto";
        String userName = "pododang";
        String password = "1234";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC DRIVER 오류 : " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/"
                    + database + "?useSSL=false", userName, password);
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
