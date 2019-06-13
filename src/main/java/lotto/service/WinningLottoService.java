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

    public int nowWinningLottoTimes() throws Exception {
        return winningLottoDao.newWinningLottoTimes();
    }

    public int nextWinningLottoTimes() throws Exception {
        return winningLottoDao.newWinningLottoTimes() + 1;
    }

    public int addWinningLotto(WinningLotto winningLotto, int times) throws Exception {
        return winningLottoDao.addWinningLotto(winningLotto, times);
    }

    public WinningLotto findByTimes(int times) throws Exception {
        return winningLottoDao.findByTimes(times);
    }
}
