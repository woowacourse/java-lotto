package domain;

import static domain.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static domain.exception.ExceptionMessage.INVALID_FORMAT;
import static domain.exception.ExceptionMessage.INVALID_RANGE;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_LENGTH = 6;

    private final List<Integer> numbers;

    public static Lotto from(final List<Integer> numbers){
        validateLength(numbers);
        validateRange(numbers);
        validateLottoDuplicate(numbers);

        return new Lotto(numbers);
    }

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int matchCount(final WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private static void validateLottoDuplicate(List<Integer> numbers) {
        int count = (int) numbers.stream()
                .distinct()
                .count();

        if (count != LOTTO_LENGTH) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private static void validateLength(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    private static void validateRange(final List<Integer> numbers) {
        if(numbers.stream()
                .anyMatch(number -> number < LOTTO_MIN || number > LOTTO_MAX)){
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }
}
