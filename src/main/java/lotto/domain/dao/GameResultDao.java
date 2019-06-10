package lotto.domain.dao;

import lotto.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class GameResultDao {
    private final Connection connection;

    public GameResultDao(Connection connection) {
        this.connection = connection;
    }

    public void addGameResult(LottoTickets lottoTickets, WinningLotto winningLotto, LottoMoney lottoMoney) throws SQLException {
        LottoResults lottoResults = new LottoResults(lottoTickets, winningLotto, lottoMoney);
        Map<LottoRank, Integer> map = lottoResults.getLottoRewards();
        double yield = lottoResults.getYield();
        String query = "INSERT INTO result(yield, rewardMoney, fifth, fourth, third, second, first)"
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, yield);
        preparedStatement.setInt(2, Integer.parseInt(lottoMoney.toString()));
        preparedStatement.setInt(3, map.get(LottoRank.FIFTH));
        preparedStatement.setInt(4, map.get(LottoRank.FOURTH));
        preparedStatement.setInt(5, map.get(LottoRank.THIRD));
        preparedStatement.setInt(6, map.get(LottoRank.SECOND));
        preparedStatement.setInt(7, map.get(LottoRank.FIRST));
        preparedStatement.executeUpdate();
    }

}
