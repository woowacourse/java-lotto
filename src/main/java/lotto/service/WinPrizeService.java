package lotto.service;

import lotto.dao.WinPrizeDao;
import lotto.domain.WinPrize;

public class WinPrizeService {
    private final WinPrizeDao winPrizeDao;

    public WinPrizeService(final WinPrizeDao winPrizeDao) {
        this.winPrizeDao = winPrizeDao;
    }

    public WinPrize getAllByRound(final int round) {
        return winPrizeDao.findAllByRound(round);
    }

    public void save(final WinPrize winPrize, final int round) {
        winPrizeDao.save(winPrize, round);
    }
}
