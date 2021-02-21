package lotto.domain.number;

import java.util.Collections;
import java.util.List;

public class LottoNumbers {
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> list() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
