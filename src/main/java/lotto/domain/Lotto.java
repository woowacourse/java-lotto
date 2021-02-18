package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoNumberException;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    protected final List<Integer> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validates(numbers);
        this.lottoNumbers = new ArrayList<>(numbers);
    }

    private void validates(List<Integer> values) {
        validateDuplicatedNumber(values);
        for (int value : values) {
            validateBoundNumber(value);
        }
    }

    private void validateDuplicatedNumber(List<Integer> values) {
        if (new HashSet<>(values).size() != LOTTO_SIZE) {
            throw new DuplicateLottoNumberException();
        }
    }

    protected void validateBoundNumber(int value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new InvalidLottoNumberException();
        }
    }

    public boolean isContainsNumber(int number) {
        return lottoNumbers.contains(number);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
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
