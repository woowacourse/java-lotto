package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.domain.WinningLotto;

import java.sql.SQLException;

public class WinningLottoService {
    private final WinningLottoDao winningLottoDao;

    public WinningLottoService(final WinningLottoDao winningLottoDao) {
        this.winningLottoDao = winningLottoDao;
    }

    public void save(final int round, final WinningLotto winningLotto) {
        winningLottoDao.save(winningLotto, round);
    }


    public WinningLotto findAllByRound(final int round) throws SQLException {
        return winningLottoDao.findAllByRound(round).orElseThrow(SQLException::new);
    }
}
