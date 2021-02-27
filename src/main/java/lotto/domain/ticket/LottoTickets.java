package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public int size() {
        return this.tickets.size();
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
