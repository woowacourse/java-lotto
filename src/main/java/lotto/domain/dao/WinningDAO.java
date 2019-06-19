package lotto.domain.dao;

import lotto.domain.lottoTicket.Lotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class WinningDAO {
    private static final Connection connection = ConnectionDB.getConnection();
    private static final int ROUND_COLUMN = 1;
    private static final int ONE_NUMBER_COLUMN = 2;
    private static final int BONUS_COLUMN = 8;

    public static void addWinningLotto(List<Integer> lottoNumbers, int bonus) throws SQLException {
        String query = "INSERT INTO winning VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(ROUND_COLUMN, RoundDAO.searchMaxCount());
        int index = ONE_NUMBER_COLUMN;
        for (Integer lottoNumber : lottoNumbers) {
            preparedStatement.setInt(index, lottoNumber);
            index++;
        }
        preparedStatement.setInt(BONUS_COLUMN, bonus);
        preparedStatement.executeUpdate();
    }

    public static Lotto searchWinningNumbers(int round) throws SQLException {
        String query = "SELECT one, two, three, four, five, six FROM winning WHERE round = ?";
        ResultSet resultSet = ConnectionDB.getResultSet(round, query);
        while (resultSet.next()) {
            return new Lotto(Arrays.asList(resultSet.getInt("one"),
                    resultSet.getInt("two"),
                    resultSet.getInt("three"),
                    resultSet.getInt("four"),
                    resultSet.getInt("five"),
                    resultSet.getInt("six")));
        }
        throw new SQLException();
    }

    public static Integer searchWinningBonus(int round) throws SQLException {
        String query = "SELECT bonus FROM winning WHERE round = ?";
        ResultSet resultSet = ConnectionDB.getResultSet(round, query);
        while (resultSet.next()) {
            return resultSet.getInt("bonus");
        }
        throw new SQLException();
    }
}
