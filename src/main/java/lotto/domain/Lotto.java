package lotto.domain;

import static lotto.common.exception.ErrorMessage.*;

import java.util.Comparator;
import java.util.List;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        validate(numbers);
        sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateNull(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (isCorrectedSize(numbers)) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static boolean isCorrectedSize(List<Integer> numbers) {
        return numbers.size() != 6;
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

    private void sortNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
