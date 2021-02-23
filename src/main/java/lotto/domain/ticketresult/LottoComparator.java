package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoComparator {
    private final LottoResult lottoResult;

    public LottoComparator(WinningTicketAndBonusNumber winningLottoNumbers,
        UserPurchase userPurchase) {
        this.lottoResult = new LottoResult(winningLottoNumbers, userPurchase);
    }

    public LottoResult getLottoResult(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getTickets()) {
            lottoResult.applyOneTicketResult(lottoTicket);
        }
        lottoResult.addAllWinningMoney();
        return lottoResult;
    }
}
