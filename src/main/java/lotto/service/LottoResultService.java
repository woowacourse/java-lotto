package lotto.service;

import lotto.dao.LottoResultDao;
import lottogame.domain.LottoResult;
import lottogame.domain.Rank;

import java.util.List;
import java.util.Map;

public class LottoResultService {
    private LottoResultService() {

    }

    public static int findPresentRound() {
        return LottoResultDao.getInstance().findPresentRound();
    }

    public static void updateLottoResult(int round, LottoResult lottoResult) {
        Map<Rank, Integer> result = lottoResult.getResult();
        LottoResultDao.getInstance().updateLottoResult(round, result);
    }

    public static void insertNewLottoRound() {
        LottoResultDao.getInstance().insertNewLottoRound();

    }

    public static Object getResultOf(LottoResult lottoResult, Rank rank) {
        Map<Rank, Integer> result = lottoResult.getResult();
        return result.get(rank);
    }

    public static List<Integer> findAllRound(int round) {
        return LottoResultDao.getInstance().findAllRound(round);
    }

    public static LottoResult findLottoResultByRound(int round) {
        Map<Rank, Integer> result = LottoResultDao.getInstance().findLottoResultByRound(round);
        return new LottoResult(result);
    }
}
