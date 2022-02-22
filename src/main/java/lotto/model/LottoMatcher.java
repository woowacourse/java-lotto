package lotto.model;

import java.util.List;

public class LottoMatcher {
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int match(Lotto lotto) {
        return lotto.match(winningNumbers);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.matchBonusNumber(bonusNumber);
    }
}
