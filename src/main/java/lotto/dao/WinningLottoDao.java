package lotto.dao;

import lotto.domain.WinningLotto;

public interface WinningLottoDao {
    void addWinningLotto(WinningLotto winningLotto, int lottoRound);

    String offerWinningNumber(int lottoRound);

    int offerBonusNumber(int lottoRound);
}
