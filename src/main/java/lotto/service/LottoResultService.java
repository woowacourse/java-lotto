package lotto.service;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.utils.ResultMessage;

import java.sql.SQLException;
import java.util.List;

public class LottoResultService {

    public LottoResult getLottoResult(Lottos lottos, WinningLotto winningLotto) {
        return LottoResult.generateLottoResult(lottos, winningLotto);
    }

    public List<String> getResultMessage(LottoResult lottoResult) {
        return ResultMessage.getResult(lottoResult);
    }

    public double getYield(LottoResult lottoResult, int round) throws SQLException {
        RoundDao roundDao = new RoundDao(new DatabaseConnection().getConnection());
        return lottoResult.findYield(roundDao.findPriceByRound(round));
    }
}
