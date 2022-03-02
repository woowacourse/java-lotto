package lotto.domain;

import lotto.Constant;

import java.util.List;

public class BonusNumber {
    private static final String DUPLICATION_ERROR_MESSAGE = "중복값이 있습니다";
    private static final String NOT_IN_RANGE_MESSAGE = "범위내에 없습니다";
    private int bonusNumber;

    public BonusNumber(String input, PickedNumbers pickedNumbers) {
        bonusNumber = Integer.parseInt(input);
        validateRange();
        validateDuplicate(pickedNumbers.getPickedNumbers());
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateDuplicate(List<Integer> pickedNumbers) {
        if (pickedNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
        }
    }

    private void validateRange() {
        if (bonusNumber < Constant.FIRST_NUM || bonusNumber > Constant.LAST_NUM) {
            throw new IllegalArgumentException(NOT_IN_RANGE_MESSAGE);
        }
    }
}
