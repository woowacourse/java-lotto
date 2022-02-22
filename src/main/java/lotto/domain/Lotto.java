package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        checkNumberSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkNumberSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
        }
    }
}
