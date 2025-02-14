package lotto.domain;

import static lotto.constant.ErrorMessage.WINNING_NUMBERS_CONTAIN_BONUS_NUMBER;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        LottoNumber generatedBonusNumber = new LottoNumber(bonusNumber);
        validateBonusNumber(lotto, generatedBonusNumber);
        this.winningLotto = lotto;
        this.bonusNumber = generatedBonusNumber;
    }

    private static void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(String.format(WINNING_NUMBERS_CONTAIN_BONUS_NUMBER.getMessage()
                    , LottoNumber.MINIMUM, LottoNumber.MAXIMUM));
        }
    }

    public Rank getRank(Lotto lotto) {
        return Rank.of(lotto.findMatchCount(winningLotto), lotto.containsNumber(bonusNumber));
    }
}
