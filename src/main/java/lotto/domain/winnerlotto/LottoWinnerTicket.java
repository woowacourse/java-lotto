package lotto.domain.winnerlotto;

import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.number.LottoNumber;

import java.util.List;

public class LottoWinnerTicket extends LottoTicket {

    public LottoWinnerTicket(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }
}
