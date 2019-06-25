package lotto.service;

import lotto.dao.LottoRoundDAO;
import lotto.dao.LottoWinningDAO;
import lotto.domain.result.Winning;
import lotto.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoWinningService {
    private static LottoRoundDAO lottoRoundDAO;
    private static LottoWinningDAO lottoWinningDAO;

    public LottoWinningService(Connection connection) {
        lottoRoundDAO = new LottoRoundDAO(connection);
        lottoWinningDAO = new LottoWinningDAO(connection);
    }

    public void saveWinningLotto(String winningLotto, String bonusBall) throws SQLException {
        lottoWinningDAO.saveWinning(winningLotto, Integer.parseInt(bonusBall), lottoRoundDAO.totalRound());
    }

    public Winning inquireWinningLotto() throws SQLException {
        return lottoWinningDAO.inquireWinning(lottoRoundDAO.totalRound());
    }

    public Winning inquireWinningLotto(int round) throws SQLException {
        return lottoWinningDAO.inquireWinning(round);
    }
}
