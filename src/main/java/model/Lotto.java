package model;

import exception.LottoExceptionType;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_MIN_RANGE = 1;
    public static final int LOTTO_MAX_RANGE = 45;

    private final List<Integer> numbers;

    public static Lotto of(final List<Integer> inputs) {
        validateArgumentsSize(inputs);
        return new Lotto(inputs);
    }

    public Lotto(final List<Integer> numbers) {
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private static void validateArgumentsSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LottoExceptionType.INVALID_LOTTO_SIZE.getMessage(LOTTO_SIZE));
        }
    }

    private void validateRange(final List<Integer> inputs) {
        inputs.stream()
                .filter(input -> LOTTO_MIN_RANGE > input || input > LOTTO_MAX_RANGE)
                .forEach(input -> {
                    throw new IllegalArgumentException(
                            LottoExceptionType.INVALID_LOTTO_RANGE.getMessage(LOTTO_MIN_RANGE, LOTTO_MAX_RANGE));
                });
    }

    private void validateDuplicate(final List<Integer> inputs) {
        HashSet<Integer> set = new HashSet<>(inputs);
        if (inputs.size() != set.size()) {
            throw new IllegalArgumentException(LottoExceptionType.LOTTO_DUPLICATE.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
