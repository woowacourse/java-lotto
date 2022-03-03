package lotto.domain;

import static lotto.domain.Lotto.MAX_BOUND;
import static lotto.domain.Lotto.MIN_BOUND;

import java.util.List;

public class BonusNumber {
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 보너스 번호가 범위내에 없습니다.";

    private final int bonusNumber;

    public BonusNumber(int value) {
        checkRange(value);
        bonusNumber = value;
    }

    private void checkRange(Integer value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
