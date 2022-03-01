package lotto.domain.ticket.generator;

import java.util.Iterator;
import java.util.List;

import lotto.domain.ticket.Ticket;

public class CustomTicketGenerator implements TicketGenerator {

    private Iterator<Ticket> ticketIterator;

    public void initTickets(final List<Ticket> tickets) {
        this.ticketIterator = tickets.iterator();
    }

    @Override
    public Ticket generateTicket() {
        final Ticket ticket = ticketIterator.next();
        final List<Integer> lottoNumbers = ticket.getBallNumbers();
        return new Ticket(lottoNumbers);
    }

}
