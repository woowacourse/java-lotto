package lotto.domain;

import lotto.domain.validator.Validator;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    protected final List<LottoNumber> lottoNumbers;

    public LottoTicket(final List<LottoNumber> lottoNumbers) {
        Validator.validateNumberCount(lottoNumbers);
        Validator.validateDistinctNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public final List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
