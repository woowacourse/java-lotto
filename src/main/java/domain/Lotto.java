package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers = new ArrayList<>();

    public Lotto(List<Integer> lottoNumbers) {
        for (Integer lottoNumber : lottoNumbers) {
            validateLottoNumber(lottoNumber);
            this.lottoNumbers.add(lottoNumber);
        }
    }

    private void validateLottoNumber(Integer lottoNumber) {
        validateNumberRange(lottoNumber);
        validateDuplicatedNumber(lottoNumber);
    }

    private void validateNumberRange(Integer lottoNumber) {
        if (lottoNumber > 45 || lottoNumber <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedNumber(Integer lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
