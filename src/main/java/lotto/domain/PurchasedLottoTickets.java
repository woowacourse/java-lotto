package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedLottoTickets {
    private final List<LottoTicket> tickets;

    public PurchasedLottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public void add(LottoTicket lottoTicket) {
        tickets.add(lottoTicket);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
