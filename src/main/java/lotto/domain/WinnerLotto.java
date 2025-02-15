package lotto.domain;

import static lotto.common.ErrorMessage.DUPLICATE_BONUS_NUMBER;

public class WinnerLotto {
    private final Lotto winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinnerLotto(Lotto winnerNumbers, LottoNumber bonusNumber) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static void validateBonusNumbers(Lotto winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    public long getMatchCount(Lotto lotto) {
        return lotto.getMatchCount(winnerNumbers);
    }


    public boolean hasBonus(Lotto lotto) {
        return lotto.hasBonusNumber(bonusNumber);
    }
}
