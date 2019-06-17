package lotto.domain;

import lotto.domain.exceptions.LottoNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    static final int NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    LottoNumbers(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
        if (new HashSet<>(numbers).size() != NUMBER_COUNT) {
            throw new LottoNumberException();
        }
    }

    public List<LottoNumber> numbers() {
        return new ArrayList<>(numbers);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
