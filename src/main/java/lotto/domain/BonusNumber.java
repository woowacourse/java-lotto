package lotto.domain;

import java.util.List;

public class BonusNumber {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatch(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < LOTTO_START_NUMBER || bonusNumber > LOTTO_END_NUMBER) {
            throw new IllegalArgumentException("보너스 번호는 " + LOTTO_START_NUMBER + "~" +
                    LOTTO_END_NUMBER + " 사이의 수를 입력해야 합니다.");
        }
    }
}
