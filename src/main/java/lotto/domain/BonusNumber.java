package lotto.domain;

import java.util.List;

public class BonusNumber {
    private int bonusNumber;

    public BonusNumber(String input, ChoiceNumber choiceNumber) {
        bonusNumber = Integer.parseInt(input);

        validateRange();
        validateDuplicate(choiceNumber.getChoiceNumbers());
    }

    private void validateDuplicate(List<Integer> pickedNumbers) {
        if (pickedNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복값이 있습니다");
        }
    }

    private void validateRange() {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("범위내에 없습니다");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
