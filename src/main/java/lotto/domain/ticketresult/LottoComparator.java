package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoComparator {
    private final LottoResult lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers, UserPurchase userPurchase) {
        this.lottoResult = new LottoResult(winningLottoNumbers, userPurchase);
    }

    public LottoResult getLottoResult(
        PurchasedLottoTickets purchasedLottoTickets) {
        for (LottoTicket purchasedOneLottoTicket : purchasedLottoTickets.getTickets()) {
            lottoResult.applyOneTicketResult(purchasedOneLottoTicket);
        }
        return lottoResult;
    }
}
