package lotto.dao.winninglotto;

import lotto.dao.DBCPDataSource;
import lotto.dto.WinningResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.dao.winninglotto.sqls.WinningResultDAOSQLs.INSERT_WINNING_RESULT;
import static lotto.dao.winninglotto.sqls.WinningResultDAOSQLs.SELECT_WINNING_RESULT_BY_LOTTO_ROUND_ID;


public class WinningResultDAO {
    public static WinningResultDTO selectWinningResultByLottoRoundId(int lottoRoundId) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WINNING_RESULT_BY_LOTTO_ROUND_ID)) {
            preparedStatement.setInt(1, lottoRoundId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            long first = resultSet.getBigDecimal("first").longValue();
            long second = resultSet.getBigDecimal("second").longValue();
            long third = resultSet.getBigDecimal("third").longValue();
            long fourth = resultSet.getBigDecimal("fourth").longValue();
            long fifth = resultSet.getBigDecimal("fifth").longValue();
            long miss = resultSet.getBigDecimal("miss").longValue();
            long roi = resultSet.getBigDecimal("roi").longValue();

            return new WinningResultDTO(lottoRoundId, first, second, third, fourth, fifth, miss, roi);
        } catch (SQLException e) {
            throw e;
        }


    }

    public static void insertWinningResult(WinningResultDTO winningResult) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WINNING_RESULT)) {
            preparedStatement.setLong(1, winningResult.getLottoRoundId());
            preparedStatement.setLong(2, winningResult.getFirst());
            preparedStatement.setLong(3, winningResult.getSecond());
            preparedStatement.setLong(4, winningResult.getThird());
            preparedStatement.setLong(5, winningResult.getFourth());
            preparedStatement.setLong(6, winningResult.getFifth());
            preparedStatement.setLong(7, winningResult.getMiss());
            preparedStatement.setLong(8, winningResult.getRoi());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
