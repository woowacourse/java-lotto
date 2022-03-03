package lotto.domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int PRICE = 1000;

    public static final int SIZE = 6;

    private final Set<Number> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream()
            .map(Number::new)
            .collect(Collectors.toSet());
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public Set<Number> getNumbers() {
        return Set.copyOf(numbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        Set<Integer> removeDuplicateNumbers = new HashSet<>(numbers);
        if (removeDuplicateNumbers.size() != SIZE) {
            throw new IllegalArgumentException("중복되지 않은 6개의 숫자가 필요합니다.");
        }
    }
}
