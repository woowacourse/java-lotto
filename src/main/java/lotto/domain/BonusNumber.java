package lotto.domain;

import lotto.domain.constant.LottoConstants;

import java.util.List;

public class BonusNumber {

    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatch(final List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private void validateRange(final int bonusNumber) {
        if (bonusNumber < LottoConstants.MIN_NUMBER || bonusNumber > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 번호는 " + LottoConstants.MIN_NUMBER + "~" +
                    LottoConstants.MAX_NUMBER + " 사이의 수를 입력해야 합니다.");
        }
    }
}
