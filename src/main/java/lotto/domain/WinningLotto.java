package lotto.domain;

import lotto.utils.Validator;

public class WinningLotto {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        Validator.validateWinningLotto(winningTicket, bonusNumber);
        this.winningTicket = new LottoTicket(winningTicket.lottoTicket());
        this.bonusNumber = new LottoNumber(bonusNumber.toString());
    }
}
