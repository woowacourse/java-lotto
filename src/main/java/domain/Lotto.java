package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Lotto {
    private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

    private List<LottoNumber> lottoNumbers;

    Lotto(List<LottoNumber> lottoNumbers) {
        validateNumberOf(lottoNumbers);
        validateNoDuplicationIn(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateNumberOf(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또를 발급받으려면 6개의 숫자가 있어야 합니다.");
        }
    }

    private void validateNoDuplicationIn(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);

        if (lottoNumberSet.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("중복되는 숫자가 들어있으면 안 됩니다.");
        }
    }

}
