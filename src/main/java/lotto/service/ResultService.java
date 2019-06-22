package lotto.service;

import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.domain.WinningStatistics;
import lotto.persistence.Connector;
import lotto.persistence.LottoDAO;
import lotto.persistence.RoundDAO;
import lotto.persistence.WinningLottoDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class ResultService {
    public static ResultDTO getResultByRoundId(int thisRoundId) throws SQLException {
        ResultDTO result = new ResultDTO();
        Connection con = Connector.getConnection();
        RoundDAO roundDao = new RoundDAO(con);
        WinningLottoDAO winningLottoDao = new WinningLottoDAO(con);
        LottoDAO lottoDao = new LottoDAO(con);

        result.setRound(thisRoundId);
        result.setRounds(roundDao.getAllIds());
        WinningLotto winningLotto = winningLottoDao.findWinningLottoByRoundId(thisRoundId);
        result.setWinningLotto(winningLotto);

        Lottos lottos = lottoDao.findLottosByRoundId(thisRoundId);
        result.setLottos(lottos);

        WinningStatistics winningStatistics = new WinningStatistics(lottos.match(winningLotto));
        result.setInterestRate(roundDao.getInterestRateOfId(thisRoundId));
        result.setPrize(roundDao.getPrizeOfId(thisRoundId));
        result.setResult(winningStatistics);

        Connector.closeConnection(con);
        return result;
    }
}
