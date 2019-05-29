package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;

    public WinningLotto(Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public int getWinning(Lotto lotto) {
        return winningLotto.calculateCountOfMatch(lotto);
    }
}
