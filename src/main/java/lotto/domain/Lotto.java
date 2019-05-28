package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.contains(null)) {
            throw new NullArgumentException("생성자의 인자로 null 을 넘길 수 없습니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }
}
