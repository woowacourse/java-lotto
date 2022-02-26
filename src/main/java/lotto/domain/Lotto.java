package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {

    private static final int SIZE = 6;
    private static final String SIZE_ERROR_MESSAGE = "6개의 숫자가 필요합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복은 허용하지 않습니다.";

    private final Set<Number> numbers;

    public Lotto() {
        List<Number> randomNumbers = Number.getRandomNumbers(SIZE);
        this.numbers = new TreeSet<>(randomNumbers);
    }

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream()
                .map(Number::getInstance)
                .collect(Collectors.toSet());
    }

    public static void validateNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        int noDuplicateCount = (int) numbers.stream()
                .distinct()
                .count();

        if (noDuplicateCount != SIZE) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public Set<Number> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }
}
