package lotto.domain.ticketresult;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class WinningLottoNumbers {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLottoNumbers(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        validateWinningTicketNotContainsBonusNumber(lottoTicket, bonusNumber);
        this.winningTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateWinningTicketNotContainsBonusNumber(LottoTicket winningTicket,
        LottoNumber bonusNumber) {
        if (winningTicket.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되지 않아야 합니다.");
        }
    }
}
