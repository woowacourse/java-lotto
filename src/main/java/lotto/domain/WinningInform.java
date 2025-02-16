package lotto.domain;

import static lotto.common.exception.ErrorMessage.*;
import static lotto.domain.LottoNumber.LOTTO_RANGE_MAXIMUM;
import static lotto.domain.LottoNumber.LOTTO_RANGE_MINIMUM;

public class WinningInform {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningInform(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public MatchResult match(Lotto lotto) {
        return lotto.countMatchingNumbers(winningLotto, bonusNumber);
    }

    private static void validate(Lotto winningLotto, int bonusNumber) {
        validateBonusRange(bonusNumber);
        validateBonusDuplicate(winningLotto, bonusNumber);
    }

    private static void validateBonusRange(int bonus) {
        if (bonus < LOTTO_RANGE_MINIMUM || bonus > LOTTO_RANGE_MAXIMUM) {
            throw new IllegalArgumentException(ERROR_BONUS_OUT_OF_RANGE);
        }
    }

    private static void validateBonusDuplicate(Lotto winningLotto, int bonus) {
        if (winningLotto.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_WINNING_AND_BONUS);
        }
    }
}