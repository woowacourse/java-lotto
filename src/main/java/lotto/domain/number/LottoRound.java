package lotto.domain.number;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LottoRound extends LottoRoundFormat {

    public LottoRound(List<LottoNumber> randomNumbers) {
        super(randomNumbers);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean has(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(containsLottoNumber -> containsLottoNumber.equals(lottoNumber));
    }
}
