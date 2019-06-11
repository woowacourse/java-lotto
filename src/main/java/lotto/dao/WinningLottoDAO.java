package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class WinningLottoDAO {

    public Lotto findWinningLottoByRound(int round) throws SQLException {
        String sql = "SELECT winning_lotto FROM winning_lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return LottoFactory.createLottoManually(
                        Arrays.asList(resultSet.getString("winning_lotto")
                                .replaceAll(" ", "")
                                .split(",")));
            }
        }
    }

    public int findBonusNumberByRound(int round) throws SQLException {
        String sql = "SELECT bonus_number FROM winning_lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt("bonus_number");
            }
        }
    }

    public void addWinningLotto(Lotto winningLotto, int bonusNumber) throws SQLException {
        String sql = "INSERT INTO winning_lotto (winning_lotto, bonus_number, round) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nextRound = new RoundDAO().findMaxRound();
            statement.setString(1, getSubstring(winningLotto.toString()));
            statement.setInt(2, bonusNumber);
            statement.setInt(3, nextRound);
            statement.executeUpdate();
        }
    }

    // TODO WinningLotto 테이블에 저장 시 중복 코드 발생하는지 확인할 것
    private String getSubstring(String lotto) {
        return lotto.substring(1, lotto.length() - 1);
    }

    public void removeWinningLotto(int round) throws SQLException {
        String sql = "DELETE FROM winning_lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        }
    }
}
