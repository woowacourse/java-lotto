package model.draw;

import exception.ExceptionMessage;
import java.util.HashSet;
import java.util.List;
import model.LottoConstants;

public class WinningNumber {
    private final List<Integer> numbers;

    public WinningNumber(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberDuplication(numbers);
        for (int number : numbers) {
            validateNumberRange(number);
        }
        this.numbers = numbers;
    }

    public boolean contains(int targetNumber) {
        return numbers.contains(targetNumber);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_LOTTO_SIZE);
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }
}
