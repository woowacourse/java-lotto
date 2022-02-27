package lotto.model.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    private static final String NUMBERS_ERROR_MESSAGE = "[ERROR] 중복되지 않은 6자리 수를 입력해주세요.";
    private static final int LOTTO_NUMBERS_SIZE = 6;

    public List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) throws RuntimeException {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) throws RuntimeException {
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

    public int countMatchingNumber(LottoNumbers winningNumbers) {
        return (int) winningNumbers.lottoNumbers.stream()
                .filter(this::containNumber)
                .count();
    }

    public boolean containNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> getLottoNumbers() {
        List<LottoNumber> newLottoNumbers = new ArrayList<>(lottoNumbers);
        return newLottoNumbers;
    }
}
