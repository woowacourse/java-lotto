package lotto.persistence;

import lotto.domain.WinningLotto;

public interface WinningLottoDAO {
    static WinningLottoDAO getInstance() {
        return WinningLottoDAOImpl.getInstance();
    }

    void addWinningLotto(int roundId, WinningLotto winningLotto);

    WinningLotto findWinningLottoByRoundId(int roundId);
}

