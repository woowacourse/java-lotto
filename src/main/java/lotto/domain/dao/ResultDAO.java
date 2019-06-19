package lotto.domain.dao;

import lotto.domain.Money;
import lotto.domain.rank.RankResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ResultDAO {
    private static final Connection connection = ConnectionDB.getConnection();
    private static final int ROUND_COLUMN = 1;
    private static final int FIRST_COLUMN = 2;
    private static final int TOTAL_MONEY_COLUMN = 7;
    private static final int YEILD_COLUMN = 8;

    public static void addResult(RankResult rank, Money money) throws SQLException {
        String query = "INSERT INTO result VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement psmt = connection.prepareStatement(query);
        psmt.setInt(ROUND_COLUMN, RoundDAO.searchMaxCount());
        List<Integer> winners = rank.getWinnersNumber();
        int firstCount = FIRST_COLUMN;
        for (Integer winner : winners) {
            psmt.setInt(firstCount, winner);
            firstCount++;
        }
        psmt.setLong(TOTAL_MONEY_COLUMN, rank.totalRewardMoney());
        psmt.setInt(YEILD_COLUMN, (int) rank.rateOfReturn(money.getMoney()));
        psmt.executeUpdate();
    }

    public static List<Integer> searchResultNumbers(int round) throws SQLException {
        String query = "SELECT first, second, third, fourth, fifth FROM result WHERE round = ?";
        ResultSet resultSet = ConnectionDB.getResultSet(round, query);
        while (resultSet.next()) {
            return Arrays.asList(
                    resultSet.getInt("fifth"),
                    resultSet.getInt("fourth"),
                    resultSet.getInt("third"),
                    resultSet.getInt("second"),
                    resultSet.getInt("first"));
        }
        throw new SQLException();
    }

    public static long searchTotalMoney(int round) throws SQLException {
        String query = "SELECT money FROM result WHERE round = ?";
        ResultSet resultSet = ConnectionDB.getResultSet(round, query);
        while (resultSet.next()) {
            return resultSet.getLong("money");
        }
        throw new SQLException();
    }

    public static long searchRateOfReturn(int round) throws SQLException {
        String query = "SELECT yeild FROM result WHERE round = ?";
        ResultSet resultSet = ConnectionDB.getResultSet(round, query);
        while (resultSet.next()) {
            return resultSet.getLong("yeild");
        }
        throw new SQLException();
    }
}