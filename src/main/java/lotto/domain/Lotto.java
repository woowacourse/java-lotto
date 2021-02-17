package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lotto.exception.DuplicateLottoNumberException;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateDuplicatedNumber(numbers);
        this.lottoNumbers = new ArrayList<>(numbers);
    }

    private void validateDuplicatedNumber(List<Integer> values) {
        if (new HashSet<>(values).size() != LOTTO_SIZE) {
            throw new DuplicateLottoNumberException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
