package lotto.service;

import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

public class WinningLottoService {
    private static final WinningLottoService INSTANCE = new WinningLottoService();

    private final WinningLottoDao winningLottoDao;
    private final RoundDao roundDao;

    private WinningLottoService() {
        winningLottoDao = WinningLottoDao.getInstance();
        roundDao = RoundDao.getInstance();
    }

    public static WinningLottoService getInstance() {
        return INSTANCE;
    }

    public void add(final WinningLotto winningLotto) {
        int nextRound = roundDao.findNext();
        winningLottoDao.add(winningLotto, nextRound);
    }

    public void deleteAll() {
        winningLottoDao.deleteAll();
    }

    public WinningLotto findByRound(final int round) {
        return winningLottoDao.findByRound(round);
    }
}
