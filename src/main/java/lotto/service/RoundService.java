package lotto.service;

import lotto.dao.RoundDao;

import java.sql.SQLException;

public class RoundService {
    private static final int SAVE_FAIL = 0;

    private final RoundDao roundDao;

    public RoundService(final RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    public int increaseOne() throws SQLException {
        if (roundDao.add() == SAVE_FAIL) {
            throw new SQLException("라운드 DB 저장 에러");
        }
        return getLatestRound();
    }

    public int getLatestRound() {
        return roundDao.getLatest();
    }
}
