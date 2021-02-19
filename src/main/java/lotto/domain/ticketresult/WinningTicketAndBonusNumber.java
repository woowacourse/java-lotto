package lotto.domain.ticketresult;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class WinningTicketAndBonusNumber {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningTicketAndBonusNumber(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        validateWinningTicketNotContainsBonusNumber(lottoTicket, bonusNumber);
        this.winningTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateWinningTicketNotContainsBonusNumber(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되지 않아야 합니다.");
        }
    }

    public List<LottoNumber> getMatchedLottoNumbers(LottoTicket lottoTicket) {
        List<LottoNumber> matchedNumbers
            = new ArrayList<>(winningTicket.getMatchedLottoNumbers(lottoTicket));
        if (lottoTicket.contains(bonusNumber)) {
            matchedNumbers.add(bonusNumber);
        }
        return matchedNumbers;
    }
}
