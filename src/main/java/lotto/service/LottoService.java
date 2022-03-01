package lotto.service;

import java.util.List;

import lotto.domain.money.Money;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.domain.ticket.generator.TicketGenerator;

public class LottoService {

    private final TicketGenerator ticketGenerator;

    public LottoService(final TicketGenerator ticketGenerator) {
        this.ticketGenerator = ticketGenerator;
    }

    public Tickets generateTickets(final int money) {
        final int ticketCount = (new Money(money)).getQuotient();
        return new Tickets(ticketCount, ticketGenerator);
    }

    public Analysis generateAnalysis(final Tickets tickets, final WinningTicket winningTicket) {
        final List<Rank> ranks = winningTicket.calculateRanks(tickets);
        return new Analysis(ranks, tickets.getSize());
    }

}
