package domain;

import static domain.LottoRules.MAX_NUMBER;
import static domain.LottoRules.MIN_NUMBER;
import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;
import static error.ErrorMessage.INVALID_DUPLICATE_NUMBER;
import static error.ErrorMessage.INVALID_NUMBERS_SIZE;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }


    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateDuplicateNumbers(numbers);
        validateNumbersRange(numbers);
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBERS_REQUIRED) {
            throw new IllegalArgumentException(INVALID_NUMBERS_SIZE.getMessage());
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> tmpNumbers = new HashSet<>(numbers);

        if (tmpNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_NUMBER.getMessage());
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
            }
        }
    }


}
