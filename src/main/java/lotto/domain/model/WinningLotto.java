package lotto.domain.model;

public class WinningLotto {

    private Lotto winningLotto;
    private Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        this.winningLotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int matchCount(Lotto purchasedLotto) {
        purchasedLotto.getLotto().retainAll(winningLotto.getLotto());
        return purchasedLotto.getLotto().size();
    }

    public boolean matchBonusNumber(Lotto purchasedLotto) {
        return purchasedLotto.getLotto().contains(bonusNumber);
    }
}
