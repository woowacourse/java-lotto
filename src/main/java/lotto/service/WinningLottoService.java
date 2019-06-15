package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

public class WinningLottoService {
    private final WinningLottoDao winningLottoDao;

    public WinningLottoService(final WinningLottoDao winningLottoDao) {
        this.winningLottoDao = winningLottoDao;
    }

    public void save(final int round, final WinningLotto winningLotto) {
        winningLottoDao.save(winningLotto, round);
    }


    public Object findAllByRound(final int round) {
        return winningLottoDao.findAllByRound(round);
    }
}
