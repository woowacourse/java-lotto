package lotto.domain.lotto;

import java.util.List;
import lotto.domain.number.LottoNumber;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplicate(lottoNumbers);
        validateLottoNumberCount(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 넘버에 중복이 있습니다.");
        }
    }

    private void validateLottoNumberCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 넘버가 6개가 아닙니다.");
        }
    }
}
