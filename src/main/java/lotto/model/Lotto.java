package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(final List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    private void validateDuplication(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    private void validateRange(final List<Integer> numbers) {
        for (final int number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException("로또는 1 이상 45 이하만 가능합니다.");
            }
        }
    }

    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
        }
    }

    public boolean has(final int number) {
        return numbers.stream()
                .anyMatch((thisNumber) -> thisNumber == number);
    }

    public int calculateMatchingCount(final Lotto otherLotto) {
        int count = 0;
        for (int number : otherLotto.numbers) {
            count = getSameCount(number, count);
        }
        return count;
    }

    private int getSameCount(final int number, int count) {
        if (this.has(number)) {
            count++;
        }
        return count;
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .sorted()
                .toList();
    }

}
