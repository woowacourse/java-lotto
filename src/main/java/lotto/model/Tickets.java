package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.OverlapException;

public class Tickets {

    private static final String OVERLAP_TICKETS_MESSAGE = "중복된 티켓이 있습니다.";
    private List<Ticket> tickets;

    public Tickets(List<Ticket> tickets) {
        validateOverlap(tickets);
        this.tickets = tickets;
    }

    private void validateOverlap(List<Ticket> tickets) {
        Set<Ticket> ticketsValidation = new HashSet<>(tickets);
        if (ticketsValidation.size() != tickets.size()) {
            throw new OverlapException(OVERLAP_TICKETS_MESSAGE);
        }
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
