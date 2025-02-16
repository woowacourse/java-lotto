package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int COUNT_OF_NUMBERS = 6;

    private static final String ERROR_DUPLICATE_NUMBERS = "로또 번호는 중복될 수 없습니다.";
    private static final String  ERROR_INVALID_COUNT_OF_NUMBERS = "로또 번호는 " + COUNT_OF_NUMBERS + "개여야 합니다.";
    private static final String ERROR_OUT_OF_RANGE = "로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + "사이여야 합니다.";

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public static Lotto of(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null || numbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException(ERROR_INVALID_COUNT_OF_NUMBERS);
        }

        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }

        for (Integer number : distinctNumbers) {
            validateRange(number);
        }
    }

    private void validateRange(final Integer number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int match(Lotto compared) {
        Set<Integer> distinctNumbers = new HashSet<>();
        distinctNumbers.addAll(compared.numbers);
        distinctNumbers.addAll(numbers);
        return (COUNT_OF_NUMBERS * 2 - distinctNumbers.size());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
