package lotto.domain;

import lotto.domain.exception.LottoSizeException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lotto {
    static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoSizeException("로또 숫자는 6개여야 합니다.");
        }

        this.numbers = numbers;
    }

    public int hasNumber(final LottoNumber number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
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
