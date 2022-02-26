package lotto.model.number;

import java.util.List;

public class LottoNumbers {

    private static final String NUMBERS_ERROR_MESSAGE = "[ERROR] 중복되지 않은 6자리 수를 입력해주세요.";
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (!isValidLength(lottoNumbers) || isDuplicate(lottoNumbers)) {
            throw new RuntimeException(NUMBERS_ERROR_MESSAGE);
        }
    }

    private boolean isValidLength(List<LottoNumber> numbers) {
        return numbers.size() == LOTTO_NUMBERS_SIZE;
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
