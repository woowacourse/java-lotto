package lotto.domain;

import lotto.domain.LottoRule.LottoNumber;
import lotto.exceptions.InvalidWinningTicketException;

public class WinningTicket {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningTicket(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new InvalidWinningTicketException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    Rank match(LottoTicket lottoTicket) {
        Rank rank = Rank.find(winningTicket.compare(lottoTicket));
        if (rank.equals(Rank.SECOND) && lottoTicket.contains(bonusNumber)) {
            return Rank.BONUS;
        }
        return rank;
    }
}
