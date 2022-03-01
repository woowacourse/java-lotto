package lotto.service;

import java.util.List;

import lotto.domain.ball.Ball;
import lotto.domain.ball.BallStorage;
import lotto.domain.money.Money;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.domain.ticket.generator.TicketGenerator;

public class LottoService {

    private final TicketGenerator ticketGenerator;

    public LottoService(final TicketGenerator ticketGenerator) {
        this.ticketGenerator = ticketGenerator;
    }

    public Ticket generateTicket(final List<Integer> ballNumbers) {
        return new Ticket(ballNumbers);
    }

    public Ball generateBall(final int ballNumber) {
        return BallStorage.getBall(ballNumber);
    }

    public Tickets generateTickets(final int money) {
        final int ticketCount = (new Money(money)).getQuotient();
        return new Tickets(ticketCount, ticketGenerator);
    }

    public Analysis generateAnalysis(final Tickets tickets, final WinningTicket winningTicket) {
        final List<Rank> ranks = tickets.calculateRanks(winningTicket);
        return new Analysis(ranks, tickets.getSize());
    }

}
