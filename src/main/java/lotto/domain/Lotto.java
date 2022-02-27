package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lotto.exception.InvalidException;

public class Lotto {

    final List<Integer> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        checkDuplicateNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        Collections.sort(lottoNumbers);
        List.of(lottoNumbers);
    }

    private static void checkDuplicateNumber(final List<Integer> numbers) {
        if (LottoNumber.LOTTO_SIZE != Set.copyOf(numbers).size()) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }

    public boolean contains(final int number) {
        return lottoNumbers.contains(number);
    }
}
