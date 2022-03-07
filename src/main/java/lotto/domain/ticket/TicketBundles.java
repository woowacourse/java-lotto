package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.TicketGenerator;
import lotto.domain.winning.WinningTicket;

public class TicketBundles {

    private final Tickets manualTickets;
    private final Tickets automaticTickets;

    private TicketBundles(final Tickets manualTickets, final Tickets automaticTickets) {
        this.manualTickets = manualTickets;
        this.automaticTickets = automaticTickets;
    }

    public static TicketBundles generateTicketBundles(final int totalTicketCount,
                                                      final List<Ticket> preparedTickets,
                                                      final TicketGenerator ticketGenerator) {
        final int restTicketCount = totalTicketCount - preparedTickets.size();
        final Tickets manualTickets = generateManualTickets(preparedTickets);
        final Tickets automaticTickets = generateAutomaticTickets(restTicketCount, ticketGenerator);
        return new TicketBundles(manualTickets, automaticTickets);
    }

    private static Tickets generateManualTickets(final List<Ticket> manualTickets) {
        return Tickets.generateTickets(manualTickets);
    }

    private static Tickets generateAutomaticTickets(final int ticketCount, final TicketGenerator ticketGenerator) {
        final List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            final Ticket ticket = ticketGenerator.generateTicket();
            tickets.add(ticket);
        }
        return Tickets.generateTickets(tickets);
    }

    public List<Rank> calculateRanks(final WinningTicket winningTicket) {
        final List<Rank> manualTicketRanks = manualTickets.calculateRanks(winningTicket);
        final List<Rank> automaticTicketRanks = automaticTickets.calculateRanks(winningTicket);
        return Stream.of(manualTicketRanks, automaticTicketRanks)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getSize() {
        return manualTickets.getSize() + automaticTickets.getSize();
    }

    public List<Ticket> getManualTickets() {
        return manualTickets.getTickets();
    }

    public List<Ticket> getAutomaticTickets() {
        return automaticTickets.getTickets();
    }

}
