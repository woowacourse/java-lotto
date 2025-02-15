package domain;

import static domain.properties.LottoProperties.COUNT_OF_NUMBERS;
import static domain.properties.LottoProperties.MAX_NUMBER;
import static domain.properties.LottoProperties.MIN_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
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
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", COUNT_OF_NUMBERS));
        }

        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }

        for (Integer number : distinctNumbers) {
            validateRange(number);
        }
    }

    private static void validateRange(final Integer number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d부터 %d사이여야 합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int match(Lotto compared) {
        Set<Integer> distinctNumbers = new HashSet<>();
        distinctNumbers.addAll(compared.numbers);
        distinctNumbers.addAll(numbers);
        return ((2 * COUNT_OF_NUMBERS) - distinctNumbers.size());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
