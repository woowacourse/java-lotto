package lotto.dao;

import lotto.domain.lotto.WinningLotto;

public interface WinningLottoDAO {
    WinningLotto findByRound(int round);

    int addWinningLotto(WinningLotto winningLotto, int round);

    int deleteWinningLotto(int round);
}
