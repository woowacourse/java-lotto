package lotto.service;

import lotto.DataBase;
import lotto.dao.MoneyDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.Money;

public class MoneyService {
    private static MoneyService instance;
    private MoneyDao moneyDao;
    private WinningLottoDao winningLottoDao;

    private MoneyService(DataBase dataBase) {
        moneyDao = new MoneyDao(dataBase);
        winningLottoDao = new WinningLottoDao(dataBase);
    }

    public static MoneyService getInstance(DataBase dataBase) {
        if (instance == null) {
            instance = new MoneyService(dataBase);
        }
        return instance;
    }

    public int addMoney(Money money) throws Exception {
        int times = winningLottoDao.nextWinningLottoTimes();
        return moneyDao.addMoney(money, times);
    }

    public Money findByTimes(int latelyTimes) throws Exception {
        return moneyDao.findByTimes(latelyTimes);
    }
}
