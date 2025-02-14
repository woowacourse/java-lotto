package domain;

import error.AppException;
import error.ErrorMessage;
import java.util.List;

public class Lotto {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateRange(numbers);
        validateDuplicate(numbers);
        validateSize(numbers);
    }

    private void validateDuplicate(final List<Integer> numbers) {
        if (isDistinctNumber(numbers)) {
            throw new AppException(ErrorMessage.INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    private boolean isDistinctNumber(final List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }

    private void validateRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new AppException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    private void validateSize(List<Integer> numbers) {
        if(numbers.size() != SIZE) {
            throw new AppException(ErrorMessage.INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    private boolean isOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
