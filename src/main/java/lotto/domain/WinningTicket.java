package lotto.domain;

import lotto.domain.LottoRule.LottoNumber;

public class WinningTicket {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningTicket(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    LottoTicket getWinningTicket() {
        return winningTicket;
    }

    int compare(LottoTicket other) {
        return winningTicket.compare(other);
    }
}
