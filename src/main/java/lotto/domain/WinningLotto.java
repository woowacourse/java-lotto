package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank getWinning(Lotto lotto) {
        return Rank.valueOf(winningLotto.calculateCountOfMatch(lotto), isMatchBonus(lotto));
    }

    private boolean isMatchBonus(Lotto lotto) {
        return lotto.isContain(bonusNumber);
    }
}
