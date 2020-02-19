package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_AMOUNT = 6;
    private static final String LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE = "로또 번호의 갯수가 6개가 아닙니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> randomNumbers) {
        Objects.requireNonNull(randomNumbers);
        validateSize(randomNumbers);
        this.lottoNumbers = randomNumbers;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_AMOUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
