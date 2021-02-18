package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;

public class LottoComparator {
    private final LottoResult lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers) {
        this.lottoResult = new LottoResult(winningLottoNumbers);
    }

    public LottoResult getLottoResult(
        PurchasedLottoTickets purchasedLottoTickets) {
        for (LottoTicket purchasedOneLottoTicket : purchasedLottoTickets.getTickets()) {
            lottoResult.applyOneTicketResult(purchasedOneLottoTicket);
        }
        return lottoResult;
    }
}
