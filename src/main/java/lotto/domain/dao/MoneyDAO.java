package lotto.domain.dao;

import lotto.domain.model.Money;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoneyDAO {
    public void addMoney(final Money money, int round) throws SQLException {
        Connection con = ConnectionGenerator.getConnection();
        String query = "INSERT INTO money (round, money, available_count) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setInt(2, money.getMoney());
        pstmt.setInt(3, money.availablePurchaseCount());
        pstmt.executeUpdate();

        ConnectionGenerator.closeConnection(con);
    }
}
