package lotto.model;

import static lotto.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.LottoConstants.Number.LOTTO_NUMBER_MIN;

import java.util.HashSet;
import java.util.Set;

public class Lotto {

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = Set.copyOf(numbers);
    }

    private void validateNumbers(Set<Integer> numbers) {
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
