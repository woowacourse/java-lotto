package lotto.domain;

import lotto.constant.WinningTier;

public class WinningLotto {

    private static final int MIN_BONUS_NUMBER = 1;
    private static final int MAX_BONUS_NUMBER = 45;
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int number) {
        if (this.lotto.hasNumber(number)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호와 중복됩니다.");
        }
        if (number < MIN_BONUS_NUMBER || number > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException("범위를 벗어난 값입니다.");
        }
    }

    public WinningTier findWinningTier(Lotto lotto) {
        int matches = this.lotto.findMatches(lotto);
        boolean hasBonusNumber = lotto.hasNumber(bonusNumber);
        return WinningTier.find(matches, hasBonusNumber);
    }
}
