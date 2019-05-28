package lotto.domain;

import lotto.domain.exception.LottoSizeException;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoSizeException("로또 숫자는 6개여야 합니다.");
        }

        this.numbers = numbers;
    }

    public int hasNumber(final int number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
