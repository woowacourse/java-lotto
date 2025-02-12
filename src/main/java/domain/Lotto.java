package domain;

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
    }

    private void validateDuplicate(final List<Integer> numbers) {
        if (isDistinctNumber(numbers)) {
            throw new IllegalArgumentException("중복된 숫자가 없어야 합니다.");
        }
    }

    private boolean isDistinctNumber(final List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }

    private void validateRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException("숫자는 1부터 45 사이여야 합니다.");
        }
    }

    private boolean isOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 0 || number > 45);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
