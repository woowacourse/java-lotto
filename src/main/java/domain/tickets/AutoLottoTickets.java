package domain.tickets;

import domain.LottoMoney;
import domain.ticket.LottoTicket;
import domain.ticket.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoTickets {
    private final List<Ticket> lottoTickets;

    public AutoLottoTickets(final LottoMoney lottoMoney) {
        final int ticketQuantity = lottoMoney.toTicketQuantity();

        this.lottoTickets = IntStream.range(0, ticketQuantity)
                .mapToObj(i -> new LottoTicket())
                .collect(Collectors.toList());
    }

    public boolean isSameQuantity(final int ticketQuantity) {
        return lottoTickets.size() == ticketQuantity;
    }
}
