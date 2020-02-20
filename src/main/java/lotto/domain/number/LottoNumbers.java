package lotto.domain.number;

import java.util.Collections;
import java.util.List;

public class LottoNumbers extends LottoRound {

    public LottoNumbers(List<LottoNumber> randomNumbers) {
        super(randomNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean isHave(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(containsLottoNumber -> containsLottoNumber.equals(lottoNumber));
    }
}
