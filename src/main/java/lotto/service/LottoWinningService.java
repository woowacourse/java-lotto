package lotto.service;

import lotto.dao.LottoRoundDAO;
import lotto.dao.LottoWinningDAO;
import lotto.domain.result.Winning;

import java.sql.SQLException;

public class LottoWinningService {
    private LottoRoundDAO lottoRoundDAO;
    private LottoWinningDAO lottoWinningDAO;

    public LottoWinningService(LottoRoundDAO lottoRoundDAO, LottoWinningDAO lottoWinningDAO) {
        this.lottoRoundDAO = lottoRoundDAO;
        this.lottoWinningDAO = lottoWinningDAO;
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
