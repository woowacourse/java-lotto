package model.lotto;

import static constant.NumberConstants.LOTTO_NUMBER_END;
import static constant.NumberConstants.LOTTO_NUMBER_START;

import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (int idx = 0; idx < numbers.size(); idx++) {
            Validator.validateOutOfRange(numbers.get(idx), LOTTO_NUMBER_START, LOTTO_NUMBER_END,
                    ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}