package lotto.model.number;

import java.util.List;

public class LottoNumbers {

    private static final String NUMBERS_ERROR_MESSAGE = "[ERROR] 중복되지 않은 6자리 수를 입력해주세요.";
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int NOT_CONTAIN_NUMBER = 0;

    private List<LottoNumber> lottoNumbers;

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

    private boolean containNumber(LottoNumber number) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.equals(number))
                .count() != NOT_CONTAIN_NUMBER;
    }

    public boolean containNumber(BonusNumber number) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.compareBonusNumber(number))
                .count() != NOT_CONTAIN_NUMBER;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
