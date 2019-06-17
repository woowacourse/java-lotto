package lotto.dao.winninglotto;

import lotto.dao.DBCPDataSource;
import lotto.dto.WinningLottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.dao.winninglotto.sqls.WinningLottoDAOSQLs.INSERT_WINNING_LOTTO;
import static lotto.dao.winninglotto.sqls.WinningLottoDAOSQLs.SELECT_WINNING_LOTTO_BY_LOTTO_ROUND_ID;

public class WinningLottoDAO {
    public static WinningLottoDTO selectWinningLottoByLottoRoundId(int lottoRoundId) throws SQLException {

        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WINNING_LOTTO_BY_LOTTO_ROUND_ID);
            preparedStatement.setInt(1, lottoRoundId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            String lottoTicket = resultSet.getString("lotto_ticket");
            int bonusNumber = resultSet.getInt("bonus_number");

            return new WinningLottoDTO(lottoTicket, bonusNumber);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void insertWinningLotto(WinningLottoDTO winningLotto, int lottoRoundId) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WINNING_LOTTO);
            preparedStatement.setInt(1, lottoRoundId);
            preparedStatement.setString(2, winningLotto.getWinningLotto());
            preparedStatement.setInt(3, winningLotto.getBonusNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
