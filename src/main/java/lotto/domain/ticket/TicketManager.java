package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.ticket.generator.TicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;

public class TicketManager {

    private final Tickets preparedTickets;
    private final Tickets generatedTickets;

    private TicketManager(final Tickets preparedTickets, final Tickets generatedTickets) {
        this.preparedTickets = preparedTickets;
        this.generatedTickets = generatedTickets;
    }

    public static TicketManager generateTickets(final int totalTicketCount,
                                          final Tickets preparedTickets,
                                          final TicketGenerator ticketGenerator) {
        final int restTicketCount = totalTicketCount - preparedTickets.getSize();
        final Tickets generatedTickets = Tickets.generateTickets(restTicketCount, ticketGenerator);
        return new TicketManager(preparedTickets, generatedTickets);
    }

    public List<Rank> calculateRanks(final WinningTicket winningTicket) {
        final List<Rank> preparedTicketRanks = preparedTickets.calculateRanks(winningTicket);
        final List<Rank> generatedTicketRanks = generatedTickets.calculateRanks(winningTicket);

        final List<Rank> ranks = new ArrayList<>();
        ranks.addAll(preparedTicketRanks);
        ranks.addAll(generatedTicketRanks);
        return ranks;
    }

    public int getSize() {
        return preparedTickets.getSize() + generatedTickets.getSize();
    }

    public List<Ticket> getPreparedTickets() {
        return preparedTickets.getTickets();
    }

    public List<Ticket> getGeneratedTickets() {
        return generatedTickets.getTickets();
    }

}
