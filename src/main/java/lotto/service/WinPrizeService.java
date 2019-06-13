package lotto.service;

import lotto.dao.WinPrizeDao;
import lotto.domain.WinPrize;

import java.sql.SQLException;

public class WinPrizeService {
    private static final int SAVE_FAIL = 0;

    private final WinPrizeDao winPrizeDao;

    public WinPrizeService(final WinPrizeDao winPrizeDao) {
        this.winPrizeDao = winPrizeDao;
    }

    public WinPrize getAllByRound(final int round) {
        return winPrizeDao.findAllByRound(round);
    }

    public void save(final WinPrize winPrize, final int round) throws SQLException {
        if (winPrizeDao.save(winPrize, round) == SAVE_FAIL) {
            throw new SQLException("우승상금 저장 에러");
        }
    }
}
