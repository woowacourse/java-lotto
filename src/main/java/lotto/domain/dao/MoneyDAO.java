package lotto.domain.dao;

import lotto.domain.model.Money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoneyDAO {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "lotto";
        String userName = "root";
        String password = "soorealbutnice";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Dirver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("con 오류 : " + e.getMessage());
        }
    }

    public void addMoney(final Money money, int round) throws SQLException {
        Connection con = getConnection();
        String query = "INSERT INTO money (round, money, available_count) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setInt(2, money.getMoney());
        pstmt.setInt(3, money.availablePurchaseCount());
        pstmt.executeUpdate();

        closeConnection(con);
    }
}
