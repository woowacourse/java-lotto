package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_DELIMITER;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;
import static lotto.util.ErrorHandler.INVALID_RANGE;
import static lotto.util.ErrorHandler.INVALID_SIZE;

import java.util.ArrayList;
import java.util.List;
import lotto.util.StringConverter;

public class Lotto {

    private List<Integer> lotto = new ArrayList<>();

    public Lotto(String input) {
        List<Integer> numbers = StringConverter.parseToIntList(input, LOTTO_NUMBER_DELIMITER);
        validate(numbers);
        this.lotto = numbers;
    }

    public Lotto(List<Integer> numbers) {
        this.lotto = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw INVALID_SIZE.getException();
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            checkRange(number);
        }
    }

    private void checkRange(int number) {
        if (number < LOTTO_NUMBER_MIN_RANGE || number > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_RANGE.getException();
        }
    }

    public List<Integer> getLotto() {
        return lotto;
    }
}
