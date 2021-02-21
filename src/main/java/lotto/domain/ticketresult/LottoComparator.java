package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketgenerator.AllPurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoComparator {
    private final LottoResult lottoResult;

    public LottoComparator(WinningTicketAndBonusNumber winningLottoNumbers, UserPurchase userPurchase) {
        this.lottoResult = new LottoResult(winningLottoNumbers, userPurchase);
    }

    public LottoResult getLottoResult(AllPurchasedLottoTickets allPurchasedLottoTickets) {
        for (LottoTicket lottoTicket : allPurchasedLottoTickets.getAllTickets()) {
            lottoResult.applyOneTicketResult(lottoTicket);
        }
        lottoResult.addAllWinningMoney();
        return lottoResult;
    }
}
