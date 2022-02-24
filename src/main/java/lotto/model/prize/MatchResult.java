package lotto.model.prize;

import lotto.model.Lotto;
import lotto.model.number.BonusNumber;
import lotto.model.number.WinningNumbers;

public class MatchResult {
    private int count;
    private boolean bonus;

    private MatchResult(int count, boolean bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public static MatchResult of(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return new MatchResult(lotto.match(winningNumbers), bonusNumber.match(lotto));
    }

    public boolean isCount(int count) {
        return this.count == count;
    }

    public boolean isBonus() {
        return bonus;
    }
}
