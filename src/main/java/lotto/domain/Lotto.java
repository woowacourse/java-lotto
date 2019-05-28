package lotto.domain;

import lotto.domain.exception.LottoSizeException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {
    static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoSizeException("로또 숫자는 6개여야 합니다.");
        }

        this.numbers = makeLottoNumbers(numbers);
    }

    private List<LottoNumber> makeLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

    public int hasNumber(final LottoNumber number) {
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
