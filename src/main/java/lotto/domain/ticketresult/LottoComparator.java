package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoComparator {
    private final LottoResult lottoResult;

    public LottoComparator(WinningTicketAndBonusNumber winningTicketAndBonusNumber,
        UserPurchase userPurchase) {
        this.lottoResult = new LottoResult(winningTicketAndBonusNumber, userPurchase);
    }

    public LottoResult getLottoResult(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getAll()) {
            lottoResult.calculateResult(lottoTicket);
        }
        lottoResult.calculateMoney();
        return lottoResult;
    }
}
