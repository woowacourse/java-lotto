package lotto.model;

public class WinningLotto {

    private Lotto winningNumbers;
    private int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto lotto) {
        int count = lotto.countMatchingNumber(winningNumbers);
        return Rank.getRank(count, lotto.winBonusNumber(bonusNumber));
    }
}
