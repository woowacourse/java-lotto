package lotto.model;

import java.util.List;

public class LottoNumbers {

    private List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (!isValidLength(lottoNumbers) || isDuplicate(lottoNumbers)) {
            throw new RuntimeException();
        }
    }

    private boolean isValidLength(List<LottoNumber> numbers) {
        return numbers.size() == 6;
    }

    private boolean isDuplicate(List<LottoNumber> numbers) {
        return numbers.size() != numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .distinct()
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
