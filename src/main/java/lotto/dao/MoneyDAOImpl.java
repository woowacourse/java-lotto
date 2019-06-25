package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.Money;

import java.sql.Connection;
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
    public Money findByRound(int round) {
        String query = "SELECT * FROM money WHERE round = ?";
        Money money = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return money;

            money = new Money(rs.getString("money"));
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("구매 금액을 가져오지 못했습니다.");
        }
        return money;
    }

    @Override
    public int addMoney(Money money, int round) {
        String query = "INSERT INTO money VALUES (?, ?)";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            pstmt.setInt(2, money.getMoney());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("로또 금액을 생성하지 못했습니다.");
        }
        return result;
    }

    @Override
    public int deleteMoney(int round) {
        String query = "DELETE FROM money WHERE round = ?";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("로또 금액을 삭제하지 못했습니다.");
        }
        return result;
    }
}
