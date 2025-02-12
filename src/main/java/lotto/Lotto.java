package lotto;

import static lotto.LottoNumberConstants.LOTTO_NUMBER_COUNT;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.Set;

public class Lotto {

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(Set<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT.value()) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        validateNumberInRange(numbers);
    }

    private void validateNumberInRange(Set<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_NUMBER_MIN.value() || number > LOTTO_NUMBER_MAX.value()) {
                throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 수여야 합니다.");
            }
        }
    }

    public Set<Integer> getNumbers() {
        return Set.copyOf(numbers);
    }
}
