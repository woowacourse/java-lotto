package lotto.model.number;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {

    private static final String NUMBERS_ERROR_MESSAGE = "[ERROR] 중복되지 않은 6자리 수를 입력해주세요.";

    public static final int LOTTO_LENGTH = 6;

    public final List<LottoNumber> lottoNumbers;

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(final List<LottoNumber> lottoNumbers) {
        if (!isValidLength(lottoNumbers) || isDuplicate(lottoNumbers)) {
            throw new IllegalArgumentException(NUMBERS_ERROR_MESSAGE);
        }
    }

    private boolean isValidLength(final List<LottoNumber> numbers) {
        return numbers.size() == LOTTO_LENGTH;
    }

    private boolean isDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> uniqueLottoNumbers = new HashSet<>(numbers);
        return numbers.size() != uniqueLottoNumbers.size();
    }

    public int countMatchingNumber(final LottoNumbers lottoNumbers) {
        return (int) lottoNumbers.lottoNumbers.stream()
                .filter(this::containNumber)
                .count();
    }

    public boolean containNumber(final LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
