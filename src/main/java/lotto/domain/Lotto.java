package lotto.domain;

import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String INVALIDATE_LOTTO_NUMBERS_ERROR_MSG = ".";
    private final int  LOTTO_NUMBERS_SIZE = 6;
    protected Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateDistinctNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDistinctNumbers(Set<LottoNumber> inputNumbers) {
        if (inputNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALIDATE_LOTTO_NUMBERS_ERROR_MSG);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
