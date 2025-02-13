package domain;

import error.AppException;
import error.ErrorMessage;
import java.util.List;

public class Lotto {
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
        if(numbers.size() != 6) {
            throw new AppException(ErrorMessage.INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    private boolean isOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
