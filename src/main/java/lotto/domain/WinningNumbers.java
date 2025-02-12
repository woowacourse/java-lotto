package lotto.domain;

public class WinningNumbers {

    private Lotto winningLotto;
    private int bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = lotto.findMatchCount(winningLotto);
        boolean matchBonus = lotto.isMatchBonus(bonusNumber);

        return Rank.of(matchCount, matchBonus);
    }
}
