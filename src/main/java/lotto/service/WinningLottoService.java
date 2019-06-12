package lotto.service;

import lotto.DataBase;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

public class WinningLottoService {
    private static WinningLottoService instance;
    private WinningLottoDao winningLottoDao;

    private WinningLottoService(DataBase dataBase) {
        winningLottoDao = new WinningLottoDao(dataBase);
    }

    public static WinningLottoService getInstance(DataBase dataBase) {
        if (instance == null) {
            instance = new WinningLottoService(dataBase);
        }
        return instance;
    }


    public int nextWinningLottoTimes() throws Exception {
        return winningLottoDao.nextWinningLottoTimes();
    }

    public int addWinningLotto(WinningLotto winningLotto) throws Exception {
        int times = winningLottoDao.nextWinningLottoTimes();
        return winningLottoDao.addWinningLotto(winningLotto, times);
    }

    public WinningLotto findByTimes(int times) throws Exception {
        return winningLottoDao.findByTimes(times);
    }
}
