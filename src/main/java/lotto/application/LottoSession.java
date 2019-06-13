package lotto.application;

import lotto.domain.lottoresult.WinningLotto;

public class LottoSession {
    private static WinningLotto winningLotto;

    public static WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public static void setWinningLotto(WinningLotto winningLotto) {
        LottoSession.winningLotto = winningLotto;
    }
}
