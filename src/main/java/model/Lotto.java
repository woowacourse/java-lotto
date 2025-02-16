package model;

import exception.LottoExceptionType;
import java.util.HashSet;
import java.util.List;

public record Lotto(List<Integer> numbers) {

    public static Lotto of(final List<Integer> inputs) {
        validateArgumentsSize(inputs);
        return new Lotto(inputs);
    }

    public Lotto {
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private static void validateArgumentsSize(final List<Integer> numbers) {
        if (numbers.size() != LottoConstant.SIZE) {
            throw new IllegalArgumentException(LottoExceptionType.INVALID_LOTTO_SIZE.getMessage(LottoConstant.SIZE));
        }
    }

    private void validateRange(final List<Integer> inputs) {
        inputs.stream()
                .filter(input -> LottoConstant.MIN_NUMBER > input || input > LottoConstant.MAX_NUMBER)
                .forEach(input -> {
                    throw new IllegalArgumentException(
                            LottoExceptionType.INVALID_LOTTO_RANGE.getMessage(LottoConstant.MIN_NUMBER,
                                    LottoConstant.MAX_NUMBER));
                });
    }

    private void validateDuplicate(final List<Integer> inputs) {
        HashSet<Integer> set = new HashSet<>(inputs);
        if (inputs.size() != set.size()) {
            throw new IllegalArgumentException(LottoExceptionType.LOTTO_DUPLICATE.getMessage());
        }
    }
}
