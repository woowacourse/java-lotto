package lotto.dao;

import lotto.domain.result.LottoRank;
import lotto.domain.result.LottoResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LottoResultDAO {
    private final Connection connection;

    public LottoResultDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveLottoResult(LottoResult lottoResult, int round) throws SQLException {
        String query = "INSERT INTO RESULT VALUES(0, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        Map<LottoRank, Integer> map = lottoResult.getMap();

        for (Map.Entry<LottoRank, Integer> lottoRankEntry : map.entrySet()) {
            preparedStatement.setString(1, lottoRankEntry.getKey().name());
            preparedStatement.setInt(2, lottoRankEntry.getValue());
            preparedStatement.setInt(3, round);
            preparedStatement.addBatch();
            preparedStatement.clearParameters();
        }
        preparedStatement.executeBatch();
    }

    public Map<LottoRank, Integer> inquireLottoResult(int round) throws SQLException {
        String query = "SELECT lotto_rank, lotto_match_count FROM result WHERE round_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<LottoRank, Integer> map = new TreeMap<>();

        while (resultSet.next()) {
            map.put(LottoRank.valueOf(resultSet.getString("lotto_rank")), resultSet.getInt("lotto_match_count"));
        }

        return map;
    }
}
