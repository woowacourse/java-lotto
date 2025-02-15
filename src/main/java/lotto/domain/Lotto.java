package lotto.domain;

import lotto.constant.ExceptionMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.validateDuplication(numbers);
        this.validateRange(numbers);
        this.validateSize(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public boolean hasNumber(int target) {
        return this.numbers.stream().anyMatch(number -> number == target);
    }

    public int findMatches(Lotto lotto) {
        return (int) numbers.stream().filter(lotto::hasNumber).count();
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> notDuplicatedNumbers = new HashSet<>(numbers);
        if (notDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isInRange = numbers.stream()
                .allMatch(number -> number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER);
        if (!isInRange) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_COUNT.getContent());
        }
    }
}
