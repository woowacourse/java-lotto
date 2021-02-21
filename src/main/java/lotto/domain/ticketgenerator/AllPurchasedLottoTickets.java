package lotto.domain.ticketgenerator;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;

public class AllPurchasedLottoTickets {
    private final LottoTickets manuallyPurchasedLottoTickets;
    private final LottoTickets automaticallyPurchasedLottoTickets;

    public AllPurchasedLottoTickets(LottoTickets manuallyPurchasedLottoTickets,
        LottoTickets automaticallyPurchasedLottoTickets) {

        this.manuallyPurchasedLottoTickets = manuallyPurchasedLottoTickets;
        this.automaticallyPurchasedLottoTickets = automaticallyPurchasedLottoTickets;
    }

    public int getNumberOfManuallyPurchasedLottoTickets() {
        return manuallyPurchasedLottoTickets.size();
    }

    public int getNumberOfAutomaticallyPurchasedLottoTickets() {
        return automaticallyPurchasedLottoTickets.size();
    }

    public List<LottoTicket> getAllTickets() {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addAll(manuallyPurchasedLottoTickets);
        lottoTickets.addAll(automaticallyPurchasedLottoTickets);
        return Collections.unmodifiableList(lottoTickets.getTickets());
    }
}
