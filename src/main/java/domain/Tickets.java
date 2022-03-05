package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Tickets {
    private final List<Ticket> tickets;

    public Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Rank> getRanks(WinningNumbers winningNumbers) {
        return tickets.stream()
                .map(winningNumbers::getTicketRank)
                .collect(Collectors.toList());
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
