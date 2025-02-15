package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.validateDuplication(numbers);
        this.validateRange(numbers);
        this.validateSize(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> notDuplicatedNumbers = new HashSet<>(numbers);
        if (notDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("중복되는 번호가 있습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isInRange = numbers.stream()
                .allMatch(number -> number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER);
        if (!isInRange) {
            throw new IllegalArgumentException("범위를 벗어난 값입니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("올바르지 않은 숫자 개수입니다.");
        }
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
}
