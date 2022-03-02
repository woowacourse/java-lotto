package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.strategy.TicketingStrategy;

public class TicketMachine {

	public static Tickets generateTickets(final TicketCount ticketCount, final List<List<Integer>> manualLottoTickets,
		final TicketingStrategy ticketingStrategy) {
		List<Ticket> manualTickets = manual(ticketCount, manualLottoTickets);
		Tickets tickets = auto(manualTickets, ticketCount, ticketingStrategy);

		return tickets;
	}

	private static List<Ticket> manual(final TicketCount ticketCount, final List<List<Integer>> lottoTickets) {
		return lottoTickets.stream()
			.map(Ticket::new)
			.collect(Collectors.toList());
	}

	private static Tickets auto(final List<Ticket> tickets, final TicketCount ticketCount,
		final TicketingStrategy ticketingStrategy) {
		for (int i = 0; i < ticketCount.getAutoCount(); i++) {
			tickets.add(new Ticket(ticketingStrategy.generate()));
		}

		return new Tickets(tickets);
	}
}
