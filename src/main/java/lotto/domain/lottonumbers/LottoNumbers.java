package lotto.domain.lottonumbers;

import java.util.HashSet;
import java.util.Set;
import lotto.domain.LottoNumber;

public abstract class LottoNumbers {
    private static final int LOTTO_NUMBER_COUNT = 6;
    static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 숫자는 6개만 입력해야 합니다";
    static final String LOTTO_NUMBER_DUPLICATED = "[ERROR] 중복된 번호는 고를 수 없습니다.";

    protected final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        validateNumberCount();
    }

    private void validateNumberCount() {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT);
        }
    }
}
