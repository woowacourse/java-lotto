package lotto.controller;

import lotto.dao.GameResultDao;
import lotto.dao.TurnDao;
import lotto.dto.GameResultDto;

public class ResultService {
    private static final ResultService INSTANCE = new ResultService();

    private final GameResultDao gameResultDao;
    private final TurnDao turnDao;

    private ResultService() {
        gameResultDao = GameResultDao.getInstance();
        turnDao = TurnDao.getInstance();
    }

    public static ResultService getInstance() {
        return INSTANCE;
    }

    public void add(final GameResultDto gameResultDto) {
        gameResultDao.add(gameResultDto, turnDao.findNext());
    }
}
