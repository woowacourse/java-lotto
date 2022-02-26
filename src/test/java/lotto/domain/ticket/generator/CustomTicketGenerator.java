package lotto.domain.ticket.generator;

import java.util.Iterator;
import java.util.List;

import lotto.domain.ticket.Ticket;
import lotto.dto.TicketDto;

public class CustomTicketGenerator implements TicketGenerator {

    private Iterator<TicketDto> ticketIterator;

    public void initNumbers(final List<TicketDto> tickets) {
        this.ticketIterator = tickets.iterator();
    }

    @Override
    public Ticket generateTicket() {
        final TicketDto ticketDto = ticketIterator.next();
        final List<Integer> lottoNumbers = ticketDto.getBallNumbers();
        return new Ticket(lottoNumbers);
    }

}
