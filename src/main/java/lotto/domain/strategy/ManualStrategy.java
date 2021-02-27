package lotto.domain.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ManualTickets;

import java.util.ArrayList;
import java.util.List;

public class ManualStrategy implements GenerateStrategy {
    private final List<LottoTicket> tickets;

    public ManualStrategy(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    @Override
    public ManualTickets generateTickets() {
        return new ManualTickets(tickets);
    }
}
