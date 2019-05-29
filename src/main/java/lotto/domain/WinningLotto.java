package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;

    public WinningLotto(Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Rank getWinning(Lotto lotto) {
        return Rank.valueOf(winningLotto.calculateCountOfMatch(lotto));
    }
}
