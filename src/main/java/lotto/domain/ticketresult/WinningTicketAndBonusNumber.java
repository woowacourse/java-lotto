package lotto.domain.ticketresult;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class WinningTicketAndBonusNumber {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningTicketAndBonusNumber(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        validate(lottoTicket, bonusNumber);
        this.winningTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validate(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되지 않아야 합니다.");
        }
    }

    public MatchedLottoNumbers getMatchedLottoNumbers(LottoTicket lottoTicket) {
        LottoNumber matchedBonusNumber = getMatchedBonusNumber(lottoTicket);
        List<LottoNumber> matchedLottoNumbersExceptBonusNumber
            = winningTicket.getMatchedLottoNumbers(lottoTicket);
        if (matchedBonusNumber != null) {
            matchedLottoNumbersExceptBonusNumber.remove(matchedBonusNumber);
        }
        return new MatchedLottoNumbers(matchedLottoNumbersExceptBonusNumber, matchedBonusNumber);
    }

    private LottoNumber getMatchedBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.getNumbers().stream()
            .filter(number -> number.equals(bonusNumber))
            .findAny()
            .orElse(null);
    }
}
