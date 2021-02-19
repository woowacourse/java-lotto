package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;

import java.util.List;

public class LottoWinnerTicket extends LottoTicket {

    public LottoWinnerTicket(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }
}
