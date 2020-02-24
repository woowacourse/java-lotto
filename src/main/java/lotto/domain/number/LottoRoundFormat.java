package lotto.domain.number;

import lotto.domain.exception.LottoNumberSizeException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoRoundFormat {
    public static final int LOTTO_NUMBER_AMOUNT = 6;
    private static final String LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE = "로또 번호의 갯수가 6개가 아닙니다.";

    List<LottoNumber> lottoNumbers;

    public LottoRoundFormat(List<LottoNumber> randomNumbers) {
        Objects.requireNonNull(randomNumbers);
        validateSize(randomNumbers);
        Collections.sort(randomNumbers);
        this.lottoNumbers = randomNumbers;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_AMOUNT) {
            throw new LottoNumberSizeException(LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
