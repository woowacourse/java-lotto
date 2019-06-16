package lotto.service;

import lotto.dao.WinPrizeDao;
import lotto.domain.WinPrize;

import java.sql.SQLException;
import java.util.Optional;

public class WinPrizeService {
    private final WinPrizeDao winPrizeDao;

    public WinPrizeService(final WinPrizeDao winPrizeDao) {
        this.winPrizeDao = winPrizeDao;
    }

    public WinPrize getAllByRound(final int round) throws SQLException {
        Optional<WinPrize> winPrize = winPrizeDao.findAllByRound(round);
        return winPrize.orElseThrow(SQLException::new);
    }

    public void save(final WinPrize winPrize, final int round) {
        winPrizeDao.save(winPrize, round);
    }
}
