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

    private LottoDAO() {}

    private static class LottoDAOHolder {
        static final LottoDAO LOTTO_DAO = new LottoDAO();
    }

    public static LottoDAO getInstance() {
        return LottoDAOHolder.LOTTO_DAO;
    }

    public List<Lotto> findLottosByRound(int round) {
        String sql = "SELECT lotto FROM lotto WHERE round = ?";
        List<Lotto> lottos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Lotto lotto = LottoFactory.createLottoManually(
                            StringUtil.convertToList(resultSet.getString("lotto"), DELIMITER));
                    lottos.add(lotto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lottos;
    }

    public void addLottos(List<Lotto> lottos, int nextRound) {
        String sql = "INSERT INTO lotto(lotto, round) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Lotto lotto : lottos) {
                statement.setString(1, StringUtil.removeBrackets(lotto.toString()));
                statement.setInt(2, nextRound);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLotto(int round) {
        String sql = "DELETE FROM lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
