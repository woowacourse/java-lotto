package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

import java.sql.SQLException;

public class WinningLottoService {
    private static final int SAVE_FAIL = 0;

    private final WinningLottoDao winningLottoDao;

    public WinningLottoService(final WinningLottoDao winningLottoDao) {
        this.winningLottoDao = winningLottoDao;
    }

    public void save(final int round, final WinningLotto winningLotto) throws SQLException {
        if (winningLottoDao.save(winningLotto, round) == SAVE_FAIL) {
            throw new SQLException("우승로또 저장 에러");
        }
    }


    public Object findAllByRound(final int round) {
        return winningLottoDao.findAllByRound(round);
    }
}
