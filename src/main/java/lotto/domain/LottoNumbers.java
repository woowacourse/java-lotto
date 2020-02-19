package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumbers extends LottoRound{

    public LottoNumbers(List<LottoNumber> randomNumbers) {
        super(randomNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
