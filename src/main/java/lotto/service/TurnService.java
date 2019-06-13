package lotto.service;

import lotto.dao.RoundDao;

import java.util.List;

public class TurnService {
    private static final TurnService INSTANCE = new TurnService();

    private final RoundDao roundDao;

    private TurnService() {
        roundDao = RoundDao.getInstance();
    }

    public static TurnService getInstance() {
        return INSTANCE;
    }


    public int findNext() {
        return roundDao.findNext();
    }

    public void deleteAll() {
        roundDao.deleteAll();
    }

    public List<Integer> findAll() {
        return roundDao.findAll();
    }
}
