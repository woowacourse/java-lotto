package lotto.service;

import lotto.dao.GameStatDao;
import lotto.dao.TurnDao;
import lotto.dto.GameStatDto;

public class StatService {
    private static final StatService INSTANCE = new StatService();

    private final GameStatDao gameStatDao;
    private final TurnDao turnDao;

    private StatService() {
        gameStatDao = GameStatDao.getInstance();
        turnDao = TurnDao.getInstance();
    }

    public static StatService getInstance() {
        return INSTANCE;
    }

    public void add(final GameStatDto gameStatDto) {
        gameStatDao.add(gameStatDto, turnDao.findNext());
    }
}
