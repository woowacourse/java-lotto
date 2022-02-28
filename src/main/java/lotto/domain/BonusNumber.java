package lotto.domain;

import static lotto.domain.ChoiceNumber.MAX_BOUND;
import static lotto.domain.ChoiceNumber.MIN_BOUND;

import java.util.List;

public class BonusNumber {
    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 보너스 번호가 선택한 숫자들과 중복한 값입니다.";
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 보너스 번호가 범위내에 없습니다.";

    private final int bonusNumber;

    public BonusNumber(String input, ChoiceNumber choiceNumber) {
        int value = convertToInt(input);
        validateBonusNumber(value, choiceNumber);
        bonusNumber = Integer.parseInt(input);
    }

    private void validateBonusNumber(int value, ChoiceNumber choiceNumber) {
        checkRange(value);
        checkDuplicate(value, choiceNumber.getChoiceNumbers());
    }

    private int convertToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자입니다.");
        }
    }

    private void checkRange(Integer value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    private void checkDuplicate(Integer value, List<Integer> pickedNumbers) {
        if (pickedNumbers.contains(value)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
