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

    public void addAll(LottoTickets lottoTickets) {
        tickets.addAll(lottoTickets.getAll());
    }

    public List<LottoTicket> getAll() {
        return Collections.unmodifiableList(tickets);
    }

    public int size() {
        return tickets.size();
    }
}
