package lotto.model;

import java.util.Collections;
import java.util.List;

public class LottoTicket extends LottoNumbers {
    public LottoTicket(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
