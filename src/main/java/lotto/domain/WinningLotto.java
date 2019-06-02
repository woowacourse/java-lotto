package lotto.domain;

import lotto.exceptions.BonusNumberException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class WinningLotto {
    private static final int COUNT_INITIALIZE = 0;

    private final Lotto winningNumbers;
    private final Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        this.winningNumbers = winningNumbers;
        if (winningNumbers.contains(bonusNumber)) {
            throw new BonusNumberException();
        }
        this.bonusNumber = bonusNumber;
    }

    public Rank resultOf(Lotto lotto) {
        int matchCount = COUNT_INITIALIZE;
        boolean bonusMatch = lotto.contains(bonusNumber);
        for (Number number : lotto) {
            matchCount = countAdder(matchCount, number);
        }
        return Rank.valueOf(matchCount, bonusMatch);
    }

    private int countAdder(int matchCount, Number lottoNumber) {
        if (winningNumbers.contains(lottoNumber)) {
            matchCount++;
        }
        return matchCount;
    }
}
