package lotto.dao;

import lotto.domain.lotto.WinningLotto;

import java.sql.SQLException;

public interface WinningLottoDAO {
    WinningLotto findByRound(int round) throws SQLException;

    int addWinningLotto(WinningLotto winningLotto, int round) throws SQLException;

    int deleteWinningLotto(int round) throws SQLException;
}
