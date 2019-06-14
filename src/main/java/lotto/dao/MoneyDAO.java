package lotto.dao;

import lotto.domain.DBConnectionController;
import lotto.domain.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyDAO {
    private final DBConnectionController controller;

    public MoneyDAO(DBConnectionController controller) {
        this.controller = controller;
    }

    public int addMoney(Money money, int round) throws SQLException {
        String query = "INSERT INTO money VALUES (?, ?)";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setInt(2, money.getMoney());
        return pstmt.executeUpdate();
    }

    public Money findByRound(int round) throws SQLException {
        String query = "SELECT * FROM money WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new Money(rs.getString("money"));
    }

    public int deleteMoney(int round) throws SQLException {
        String query = "DELETE FROM money WHERE round = ?";
        PreparedStatement pstmt = controller.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
