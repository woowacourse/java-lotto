package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyDAOImpl implements MoneyDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class MoneyDAOImplHolder {
        private static final MoneyDAO instance = new MoneyDAOImpl();
    }

    public static MoneyDAO getInstance() {
        return MoneyDAOImplHolder.instance;
    }

    @Override
    public Money findByRound(int round) throws SQLException {
        String query = "SELECT * FROM money WHERE round = ?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new Money(rs.getString("money"));
    }

    @Override
    public int addMoney(Money money, int round) throws SQLException {
        String query = "INSERT INTO money VALUES (?, ?)";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setInt(2, money.getMoney());
        return pstmt.executeUpdate();
    }

    @Override
    public int deleteMoney(int round) throws SQLException {
        String query = "DELETE FROM money WHERE round = ?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
