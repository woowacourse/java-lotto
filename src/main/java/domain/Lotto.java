package domain;

import static domain.LottoGenerator.COUNT_OF_NUMBERS;
import static domain.LottoGenerator.MAX_NUMBER;
import static domain.LottoGenerator.MIN_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto of(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null || numbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }

        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }

        for (Integer number : distinctNumbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) { // TODO : indent 줄이기
                throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 합니다.");
            }
        }
    }
}
