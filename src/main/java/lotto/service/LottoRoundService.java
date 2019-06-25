package lotto.service;

import lotto.dao.LottoRoundDAO;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LottoRoundService {
    private static LottoRoundDAO lottoRoundDAO;

    public LottoRoundService(Connection connection) {
        lottoRoundDAO = new LottoRoundDAO(connection);
    }

    public void playLottoGame() throws SQLException {
        lottoRoundDAO.playRound();
    }

    public LottoResult createLottoResult(Winning winning, Lottos lottos) {
        return LottoResult.of(winning, lottos);
    }

    public List<Integer> inquireTotalRound() throws SQLException {
        return lottoRoundDAO.inquireTotalRound();
    }
}
