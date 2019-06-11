package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoDAO {
    // TODO resultSet의 output 처리 로직 중복 여부 체크
    public List<Lotto> findLottosByRound(int round) throws SQLException {
        String sql = "SELECT lotto FROM lotto WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Lotto> lottos = new ArrayList<>();
                while (resultSet.next()) {
                    Lotto lotto = LottoFactory.createLottoManually(
                            Arrays.asList(resultSet.getString("lotto")
                                    .replaceAll(" ", "")
                                    .split(",")));
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
                statement.setString(1, getSubstring(lotto.toString()));
                statement.setInt(2, nextRound);
                statement.executeUpdate();
            }
        }
    }

    // TODO WinningLotto 테이블에 저장 시 중복 코드 발생하는지 확인할 것
    private String getSubstring(String lotto) {
        return lotto.substring(1, lotto.length() - 1);
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
