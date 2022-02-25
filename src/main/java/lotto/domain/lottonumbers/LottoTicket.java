package lotto.domain.lottonumbers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoNumber;

public class LottoTicket extends LottoNumbers {
    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
