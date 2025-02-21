package lotto.domain;

import static lotto.common.constant.ErrorMessage.*;

import java.util.List;

public class WinningLotto extends Lotto {
    private final int bonus;

    public WinningLotto(List<Integer> numbers, int bonus) {
        super(numbers);
        validateBonus(bonus);
        this.bonus = bonus;
    }

    public void validateBonus(int bonus) {
        validateLottoNumberRange(bonus);
        validateDuplicatedBonus(bonus);
    }

    private void validateLottoNumberRange(int number) {
        if (isNumberInRage(number)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void validateDuplicatedBonus(int bonus) {
        if (isContainsBonus(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_BONUS_NUMBER.getMessage());
        }
    }

    public int getBonus() {
        return bonus;
    }
}
