package domain;

import static domain.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static domain.exception.ExceptionMessage.INVALID_FORMAT;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_LENGTH = 6;

    private final List<LottoNumber> numbers;

    public static Lotto from(final List<Integer> numbers) {
        validateLength(numbers);
        validateLottoDuplicate(numbers);

        return new Lotto(numbers.stream()
                .map(LottoNumber::from)
                .toList());
    }

    private Lotto(final List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public boolean contains(final LottoNumber number) {
        return numbers.contains(number);
    }

    public int matchCount(final WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public ArrayList<LottoNumber> getNumbers() {
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

}
