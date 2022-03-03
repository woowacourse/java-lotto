package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.dto.ManualTicketsDto;
import domain.strategy.TicketingStrategy;

public class TicketMachine {

	public static Tickets generateTickets(final TicketCounter ticketCounter,
		final ManualTicketsDto manualTicketsDto,
		final TicketingStrategy ticketingStrategy) {

		List<Ticket> manualTickets = doManually(manualTicketsDto.getManualTickets());
		Tickets tickets = doAutomatically(manualTickets, ticketCounter, ticketingStrategy);

		return tickets;
	}

	private static List<Ticket> doManually(final List<List<Integer>> lottoTickets) {
		return lottoTickets.stream()
			.map(Ticket::new)
			.collect(Collectors.toList());
	}

	private static Tickets doAutomatically(final List<Ticket> tickets, final TicketCounter ticketCounter,
		final TicketingStrategy ticketingStrategy) {
		for (int i = 0; i < ticketCounter.getAutoCount(); i++) {
			tickets.add(new Ticket(ticketingStrategy.generate()));
		}

		return new Tickets(tickets);
	}
}
