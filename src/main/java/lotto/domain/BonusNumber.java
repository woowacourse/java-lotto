package lotto.domain;

import java.util.List;

public class BonusNumber {

    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatch(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이의 수를 입력해야 한다.");
        }
    }
}
