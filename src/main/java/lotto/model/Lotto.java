package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Lotto {

    private final SortedSet<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = Collections.unmodifiableSortedSet(new TreeSet<>(numbers));
    }

    private void validateNumbers(Set<Integer> numbers) {
        Objects.requireNonNull(numbers, "로또 번호는 null이 될 수 없습니다.");
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
        for (int number : numbers) {
            validateNumberInRange(number);
        }
    }

    private void validateNumberInRange(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(
                    "로또 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
    }

    public int countMatchingNumbers(Lotto otherLotto) {
        Set<Integer> thisNumbers = new HashSet<>(numbers);
        thisNumbers.retainAll(otherLotto.numbers);
        return thisNumbers.size();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }
}
