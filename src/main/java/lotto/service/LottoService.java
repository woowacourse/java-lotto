package lotto.service;

import lotto.DataBase;
import lotto.dao.LottosDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.Lottos;

public class LottoService {
    private static LottoService instance;
    private LottosDao lottosDao;
    private WinningLottoDao winningLottoDao;

    private LottoService(DataBase dataBase) {
        lottosDao = new LottosDao(dataBase);
        winningLottoDao = new WinningLottoDao(dataBase);
    }

    public static LottoService getInstance(DataBase dataBase) {
        if (instance == null) {
            instance = new LottoService(dataBase);
        }
        return instance;
    }

    public void addLottos(Lottos lottos) throws Exception {
        int times = winningLottoDao.nextWinningLottoTimes();

        lottosDao.deleteLottos(times);
        lottosDao.addLottos(lottos, times);
    }

    public Lottos getLottos() throws Exception {
        int times = winningLottoDao.nextWinningLottoTimes();
        return lottosDao.findByTimes(times);
    }

    public Lottos getLottos(int times) throws Exception {
        return lottosDao.findByTimes(times);
    }
}
