package lotto.domain;

import java.util.Collections;
import java.util.Set;

public class Lotto {

    private static final String NUMBER_SIZE_ERROR = "[ERROR] 중복되지 않는 6개의 숫자를 입력해야 합니다.";
    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(NUMBER_SIZE_ERROR);
        }
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
