package lotto;

import java.util.List;

public class Lotto {

    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNumberCount(numbers);
        validateLottoRange(numbers);
        validateDistinct(numbers);
        this.numbers = numbers;
    }

    private void validateNumberCount(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("잘못된 개수의 입력입니다.");
        }
    }

    private void validateLottoRange(final List<Integer> numbers) {
        if (numbers.stream()
            .anyMatch(number -> number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException("범위가 초과된 숫자 입력입니다.");
        }
    }

    private void validateDistinct(final List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자 입력입니다.");
        }
    }
}
