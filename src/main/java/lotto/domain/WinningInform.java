package lotto.domain;

import static lotto.common.exception.ErrorMessage.*;

public class WinningInform {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningInform(Lotto winningLotto, LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public MatchResult match(Lotto lotto) {
        return lotto.countMatchingNumbers(winningLotto, bonusNumber);
    }

    private static void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        validateBonusDuplicate(winningLotto, bonusNumber);
    }

    private static void validateBonusDuplicate(Lotto winningLotto, LottoNumber bonus) {
        if (winningLotto.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_WINNING_AND_BONUS);
        }
    }
}