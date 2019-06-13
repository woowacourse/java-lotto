package lotto.service;

import lotto.dao.StatDao;
import lotto.dao.RoundDao;
import lotto.dto.StatDto;

public class StatService {
    private static final StatService INSTANCE = new StatService();

    private final StatDao statDao;
    private final RoundDao roundDao;

    private StatService() {
        statDao = StatDao.getInstance();
        roundDao = RoundDao.getInstance();
    }

    public static StatService getInstance() {
        return INSTANCE;
    }

    public void add(final StatDto statDto) {
        statDao.add(statDto, roundDao.findNext());
    }

    public void deleteAll() {
        statDao.deleteAll();
    }

    public StatDto findByRound(final int round) {
        return statDao.findByRound(round);
    }
}
