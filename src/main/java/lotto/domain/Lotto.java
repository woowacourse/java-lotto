package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.utils.Validation;

public class Lotto {

    final List<Integer> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        Validation.checkDuplicateNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        Collections.sort(lottoNumbers);
        List.of(lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }

    boolean contains(int number) {
        return lottoNumbers.contains(number);
    }
}
