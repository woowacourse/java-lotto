package lotto.domain;

import static lotto.constant.ErrorMessage.WINNING_NUMBERS_CONTAIN_BONUS_NUMBER;

public class WinningNumbers {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        LottoNumber generatedBonusNumber = new LottoNumber(bonusNumber);
        validateWinningNumbersContainBonusNumber(winningLotto, generatedBonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = generatedBonusNumber;
    }

    private static void validateWinningNumbersContainBonusNumber(Lotto lotto, LottoNumber generatedBonusNumber) {
        if (lotto.containsNumber(generatedBonusNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_NUMBER.getMessage());
        }
    }

    public Rank getRank(Lotto lotto) {
        return Rank.of(lotto.findMatchCount(winningLotto), lotto.containsNumber(bonusNumber));
    }
}
