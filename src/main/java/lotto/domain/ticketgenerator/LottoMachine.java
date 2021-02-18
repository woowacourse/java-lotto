package lotto.domain.ticketgenerator;

import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;

    public LottoMachine() {
        this.lottoGenerator = new LottoGenerator();
    }

    public PurchasedLottoTickets purchaseLottoTicket(UserPurchase userPurchase) {
        return lottoGenerator.generatePurchasedTickets(userPurchase.getNumberOfTickets());
    }
}
