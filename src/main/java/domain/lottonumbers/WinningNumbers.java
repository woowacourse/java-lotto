package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;
import domain.result.LottoRank;

public class WinningNumbers {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank findLottoRank(LottoTicket lottoTicket) {
        int matchingNumber = this.findNumberOfMatchingNumbers(lottoTicket);
        boolean hasBonus = lottoTicket.contains(bonusNumber);

        return LottoRank.valueOf(matchingNumber, hasBonus);
    }

    private int findNumberOfMatchingNumbers(LottoTicket comparingTicket) {
        return this.winningTicket.findDuplicatedNumbers(comparingTicket);
    }
}