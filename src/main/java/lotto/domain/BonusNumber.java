package lotto.domain;

import java.util.List;

public class BonusNumber {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 보너스 번호가 선택한 숫자들과 중복한 값입니다.";
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 보너스 번호가 범위내에 없습니다.";

    private final int bonusNumber;

    public BonusNumber(String input, ChoiceNumber choiceNumber) {
        validateBonusNumber(input, choiceNumber);
        bonusNumber = Integer.parseInt(input);
    }

    private void validateBonusNumber(String input, ChoiceNumber choiceNumber) {
        isInteger(input);
        int value = Integer.parseInt(input);
        isInRange(value);
        isDuplicate(value, choiceNumber.getChoiceNumbers());
    }

    private void isInteger(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자입니다.");
        }
    }

    private void isInRange(Integer value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    private void isDuplicate(Integer value, List<Integer> pickedNumbers) {
        if (pickedNumbers.contains(value)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
