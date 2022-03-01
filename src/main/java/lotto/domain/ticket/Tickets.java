package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lotto.domain.ticket.generator.TicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;

public class Tickets {

    private final List<Ticket> tickets;

    private Tickets(final List<Ticket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static Tickets generateTickets(final int totalTicketCount,
                                          final List<Ticket> preparedTickets,
                                          final TicketGenerator ticketGenerator) {
        final int restTicketCount = totalTicketCount - preparedTickets.size();
        final List<Ticket> generatedTickets = generateTickets(restTicketCount, ticketGenerator);
        final List<Ticket> tickets = concatTickets(preparedTickets, generatedTickets);
        return new Tickets(tickets);
    }

    private static List<Ticket> generateTickets(final int ticketCount, final TicketGenerator ticketGenerator) {
        final List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            final Ticket ticket = ticketGenerator.generateTicket();
            tickets.add(ticket);
        }
        return tickets;
    }

    private static List<Ticket> concatTickets(final List<Ticket> preparedTickets, final List<Ticket> generatedTickets) {
        final List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(new ArrayList<>(preparedTickets));
        tickets.addAll(new ArrayList<>(generatedTickets));
        return tickets;
    }

    public List<Rank> calculateRanks(final WinningTicket winningTicket) {
        return tickets.stream()
                .map(winningTicket::calculateRank)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Ticket> getTickets() {
        return List.copyOf(tickets);
    }

    public int getSize() {
        return tickets.size();
    }

}
