package lotto.domain;

import java.util.List;

public class BonusNumber {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 보너스 번호가 선택한 숫자들과 중복한 값입니다.";
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 보너스 번호가 범위내에 없습니다.";

    private int bonusNumber;

    public BonusNumber(String input, ChoiceNumber choiceNumber) {
        bonusNumber = Integer.parseInt(input);

        validateRange();
        validateDuplicate(choiceNumber.getChoiceNumbers());
    }

    private void validateDuplicate(List<Integer> pickedNumbers) {
        if (pickedNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    private void validateRange() {
        if (bonusNumber < MIN_BOUND || bonusNumber > MAX_BOUND) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
