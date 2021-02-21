package lotto.domain.ticketpurchase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoTicket;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public void add(LottoTicket lottoTicket) {
        tickets.add(lottoTicket);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public int size() {
        return tickets.size();
    }
}
