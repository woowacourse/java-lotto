package lotto.domain.ticketpurchase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;

public class PurchasedLottoTickets {
    private final List<LottoTicket> tickets;

    public PurchasedLottoTickets(List<LottoTicket> lottoTickets) {
        this.tickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
