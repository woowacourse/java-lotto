package lotto.service;

import lotto.dao.LottoResultDAO;
import lottogame.domain.LottoResult;
import lottogame.domain.Rank;

import java.util.Map;

public class LottoResultService {
    public static int findPresentRound() {
        return LottoResultDAO.getInstance().findPresentRound();
    }

    public static void updateLottoResult(int round, LottoResult lottoResult) {
        Map<Rank, Integer> result = lottoResult.getResult();
        LottoResultDAO.getInstance().updateLottoResult(round, result);
    }

    public static void insertNewLottoRound() {
        LottoResultDAO.getInstance().insertNewLottoRound();

    }
}
