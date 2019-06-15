package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {

    private static final String DELIMITER = ",";

    public Lotto findWinningLottoByRound(int round) {
        String sql = "SELECT winning_lotto FROM winning_lotto WHERE round = ?";
        Lotto lotto = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                lotto = LottoFactory.createLottoManually(
                        StringUtil.convertToList(resultSet.getString("winning_lotto"), DELIMITER));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lotto;
    }

    public int findBonusNumberByRound(int round) {
        String sql = "SELECT bonus_number FROM winning_lotto WHERE round = ?";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                }
                result = resultSet.getInt("bonus_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addWinningLotto(Lotto winningLotto, int bonusNumber) {
        String sql = "INSERT INTO winning_lotto (winning_lotto, bonus_number, round) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nextRound = new RoundDAO().findMaxRound();
            statement.setString(1, StringUtil.removeBrackets(winningLotto.toString()));
            statement.setInt(2, bonusNumber);
            statement.setInt(3, nextRound);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeWinningLotto(int round) {
        String sql = "DELETE FROM winning_lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
