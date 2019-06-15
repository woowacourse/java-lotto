package lotto.service;

import lotto.dao.RoundDao;

public class RoundService {
    private final RoundDao roundDao;

    public RoundService(final RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    public int increaseOne() {
        return getLatestRound();
    }

    public int getLatestRound() {
        return roundDao.getLatest();
    }
}
