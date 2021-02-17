package lotto;

import java.util.List;

public class LottoWinnerTicket extends LottoTicket {

    public LottoWinnerTicket(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public boolean isContain(LottoWinnerBonusNumber bonusNumber) {
        return this.getLottoNumbers().contains(bonusNumber);
    }
}
