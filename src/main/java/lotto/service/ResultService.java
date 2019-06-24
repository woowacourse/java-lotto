package lotto.service;

import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.domain.WinningStatistics;
import lotto.persistence.*;

public class ResultService {
    public static ResultDTO getResultByRoundId(int thisRoundId) {
        ResultDTO result = new ResultDTO();

        RoundDAO roundDao = RoundDAO.getInstance();
        WinningLottoDAO winningLottoDao = WinningLottoDAO.getInstance();
        LottoDAO lottoDao = LottoDAO.getInstance();

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
        return result;
    }
}
