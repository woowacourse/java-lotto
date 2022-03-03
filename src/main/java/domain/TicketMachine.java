package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.strategy.TicketingStrategy;

public class TicketMachine {

	public static Tickets generateTickets(final TicketCounter ticketCounter, final List<List<Integer>> manualLottoTickets,
		final TicketingStrategy ticketingStrategy) {
		List<Ticket> manualTickets = manual(manualLottoTickets);
		Tickets tickets = auto(manualTickets, ticketCounter, ticketingStrategy);

		return tickets;
	}

	private static List<Ticket> manual(final List<List<Integer>> lottoTickets) {
		return lottoTickets.stream()
			.map(Ticket::new)
			.collect(Collectors.toList());
	}

	private static Tickets auto(final List<Ticket> tickets, final TicketCounter ticketCounter,
		final TicketingStrategy ticketingStrategy) {
		for (int i = 0; i < ticketCounter.getAutoCount(); i++) {
			tickets.add(new Ticket(ticketingStrategy.generate()));
		}

		return new Tickets(tickets);
	}
}
