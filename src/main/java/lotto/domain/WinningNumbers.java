package lotto.domain;

import lotto.constant.ErrorMessage;

import static lotto.constant.ErrorMessage.*;

public class WinningNumbers {

    private Lotto winningLotto;
    private int bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        if (lotto.containsNumber(bonusNumber)) {
           throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_NUMBER.getMessage());
        }
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = lotto.findMatchCount(winningLotto);
        boolean matchBonus = lotto.isMatchBonus(bonusNumber);

        return Rank.of(matchCount, matchBonus);
    }
}
