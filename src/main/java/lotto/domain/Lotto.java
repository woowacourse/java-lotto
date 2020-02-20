package lotto.domain;

import java.util.List;

public class Lotto {
    private static final String NOT_DISTINCT_NUMBERS_ERROR_MSG = "중복되는 로또 번호가 존재합니다.";
    protected List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateDistinctNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDistinctNumbers(List<LottoNumber> inputNumbers) {
        if (inputNumbers.stream().mapToInt(LottoNumber::getNumber).distinct().count() != inputNumbers.size()) {
            throw new IllegalArgumentException(NOT_DISTINCT_NUMBERS_ERROR_MSG);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
