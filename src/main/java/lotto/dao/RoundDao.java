package lotto.dao;

import lotto.domain.Money;
import lotto.util.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDao {
    public static void addRound(int roundNo, Money money) {
        try {
            String query = "INSERT INTO round(ROUND_NO, MONEY, DATE)" +
                    " VALUES (?, ?, NOW())";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, roundNo);
            pstmt.setInt(2, money.getMoney());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
    }

    public static Money findByRoundNo(int roundNo) {
        try {
            String query = "SELECT * FROM round WHERE ROUND_NO = ?";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, roundNo);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return null;

            return new Money(rs.getInt("MONEY"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnector.closeConnection();
        }
    }

    public static Integer findMaxRounNo() {
        try {
            String query = "SELECT MAX(ROUND_NO) AS max_round_no FROM round";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return 0;

            return rs.getInt("max_round_no");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnector.closeConnection();
        }
    }
}
