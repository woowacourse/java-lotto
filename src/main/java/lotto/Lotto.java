package lotto;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersCount(numbers);
        validateNumbersRange(numbers);
        validateNumbersDuplication(numbers);
    }

    private void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또의 숫자는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (getOutOfRangeCount(numbers) > 0) {
            throw new IllegalArgumentException("로또의 숫자는 1~45이어야 합니다.");
        }
    }

    private long getOutOfRangeCount(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number < 1)
                .filter(number -> number > 45)
                .count();
    }

    private void validateNumbersDuplication(List<Integer> numbers) {
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또의 숫자는 중복될 수 없습니다.");
        }
    }

    private long getDistinctCount(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count();
    }


    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public Rank compare(Lotto winningLotto, int bonusNumber) {
        int count = (int) this.numbers.stream()
                .filter(number -> winningLotto.numbers.contains(number))
                .count();

        if (count == 5 && this.numbers.contains(bonusNumber)) {
            return Rank.SECOND;
        }

        return Rank.of(count);
    }
}