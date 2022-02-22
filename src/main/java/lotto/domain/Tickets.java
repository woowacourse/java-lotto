package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tickets {
    private final List<Ticket> tickets;

    private Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static Tickets buyTicketsByAuto(int ticketCount) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(Ticket.getTicketByAuto());
        }
        return new Tickets(tickets);
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
