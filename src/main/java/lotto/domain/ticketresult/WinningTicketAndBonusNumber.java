package lotto.domain.ticketresult;

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

    private void validateWinningTicketNotContainsBonusNumber(LottoTicket winningTicket,
        LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되지 않아야 합니다.");
        }
    }

    public MatchedLottoNumbers getMatchedLottoNumbers(LottoTicket purchasedLottoTicket) {
        LottoNumber matchedBonusNumber = getMatchedBonusNumber(purchasedLottoTicket);
        List<LottoNumber> matchedLottoNumbersNotIncludingBonusNumber
            = winningTicket.getMatchedLottoNumbers(purchasedLottoTicket);
        if (matchedBonusNumber != null) {
            matchedLottoNumbersNotIncludingBonusNumber.remove(matchedBonusNumber);
        }
        return new MatchedLottoNumbers(matchedLottoNumbersNotIncludingBonusNumber,
            matchedBonusNumber);
    }

    private LottoNumber getMatchedBonusNumber(LottoTicket purchasedLottoTicket) {
        return purchasedLottoTicket.getLottoNumbers()
            .stream()
            .filter(lottoNumber -> lottoNumber.equals(bonusNumber))
            .findAny()
            .orElse(null);
    }
}
