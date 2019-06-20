package lotto.service;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.utils.ResultMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoResultService {

    public LottoResult getLottoResult(Lottos lottos, WinningLotto winningLotto) {
        return LottoResult.generateLottoResult(lottos, winningLotto);
    }

    public List<String> getResultMessage(LottoResult lottoResult) {
        return ResultMessage.getResult(lottoResult, getRanks());
    }

    private static List<Rank> getRanks() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIRST);
        ranks.add(Rank.SECOND);
        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);
        return ranks;
    }

    public double getYield(LottoResult lottoResult, int round) throws SQLException {
        RoundDao roundDao = new RoundDao(new DatabaseConnection().getConnection());
        return lottoResult.findYield(roundDao.findPriceByRound(round));
    }
}
