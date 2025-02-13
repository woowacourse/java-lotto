package lotto.domain;

import static lotto.exception.ErrorMessage.*;

import java.util.List;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        validate(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 0 || number > 45) {
                throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
            }
        }
    }

    public boolean isContainsBonus(int bonus) {
        return numbers.contains(bonus);
    }
}
