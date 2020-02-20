package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.exception.DuplicatedNumberException;
import lotto.exception.NotEnoughNumberException;

import java.util.Collections;
import java.util.List;

public class Lotto {
    protected List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateDistinctNumbers(lottoNumbers);
        validateNumberCount(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDistinctNumbers(List<LottoNumber> inputNumbers) {
        if (inputNumbers.stream().mapToInt(LottoNumber::getNumber).distinct().count() != inputNumbers.size()) {
            throw new DuplicatedNumberException(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }

    private void validateNumberCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new NotEnoughNumberException(ErrorMessage.NUMBER_COUNT_NOT_SIX.getMessage());
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
