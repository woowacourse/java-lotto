package lotto.service;

import lotto.DataBase;
import lotto.dao.LottosDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class LottoResultService {
    private static LottoResultService instance;
    private LottosDao lottosDao;
    private WinningLottoDao winningLottoDao;

    private LottoResultService(DataBase dataBase) {
        lottosDao = new LottosDao(dataBase);
        winningLottoDao = new WinningLottoDao(dataBase);
    }

    public static LottoResultService getInstance(DataBase dataBase) {
        if (instance == null) {
            instance = new LottoResultService(dataBase);
        }
        return instance;
    }

    public LottoResult findByTimes(int times) throws Exception{
        Lottos lottos = lottosDao.findByTimes(times);
        WinningLotto winningLotto = winningLottoDao.findByTimes(times);
        return new LottoResult(lottos.getLottos(), winningLotto);
    }
}
