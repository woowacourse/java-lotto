package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
    private static final String DELIMITER = ",";

    public List<Lotto> findLottosByRound(int round) throws SQLException {
        String sql = "SELECT lotto FROM lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Lotto> lottos = new ArrayList<>();
                while (resultSet.next()) {
                    Lotto lotto = LottoFactory.createLottoManually(
                            StringUtil.convertToList(resultSet.getString("lotto"), DELIMITER));
                    lottos.add(lotto);
                }
                return lottos;
            }
        }
    }

    public void addLottos(List<Lotto> lottos) throws SQLException {
        String sql = "INSERT INTO lotto(lotto, round) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nextRound = new RoundDAO().findMaxRound();
            for (Lotto lotto : lottos) {
                statement.setString(1, StringUtil.removeBrackets(lotto.toString()));
                statement.setInt(2, nextRound);
                statement.executeUpdate();
            }
        }
    }

    public void removeLotto(int round) throws SQLException {
        String sql = "DELETE FROM lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        }
    }
}
