package lotto.dao;

import lotto.domain.LottosResult;

public interface LottoResultDAO {
    long findByRound(int round);

    int addLottoResult(LottosResult result, int round);

    int deleteLottoResult(int round);
}
