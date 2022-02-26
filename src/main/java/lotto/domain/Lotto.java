package lotto.domain;

import java.util.HashSet;
import java.util.List;

import lotto.domain.vo.Number;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<Number> numbers;

    public Lotto(List<Number> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<Number> numbers) {
        validateSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    private void validateSize(List<Number> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리 이어야 한다.");
        }
    }

    private void validateDuplicateNumbers(List<Number> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }
    }

    public int countMatchNumbers(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean containsNumber(Number number) {
        return numbers.contains(number);
    }

    public List<Number> getNumbers() {
        return List.copyOf(numbers);
    }
}
