package lotto.model.lottonumbers;

import java.util.Collections;
import java.util.List;
import lotto.model.LottoNumber;

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
