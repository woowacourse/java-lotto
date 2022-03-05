package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.dto.ManualTicketsDto;
import domain.strategy.TicketingStrategy;

public class TicketMachine {

	public static Tickets buyTickets(final TicketCounter ticketCounter,
		final ManualTicketsDto manualTicketsDto,
		final TicketingStrategy ticketingStrategy) {

		List<Ticket> manualTickets = generateManualTickets(manualTicketsDto.getManualTickets());
		List<Ticket> manualAndAutoTickets = generateAutoTickets(manualTickets, ticketCounter, ticketingStrategy);

		return new Tickets(manualAndAutoTickets);
	}

	private static List<Ticket> generateManualTickets(final List<List<Integer>> lottoTickets) {
		return lottoTickets.stream()
			.map(Ticket::new)
			.collect(Collectors.toList());
	}

	private static List<Ticket> generateAutoTickets(final List<Ticket> tickets, final TicketCounter ticketCounter,
		final TicketingStrategy ticketingStrategy) {
		for (int i = 0; i < ticketCounter.getAutoCount(); i++) {
			tickets.add(new Ticket(ticketingStrategy.generate()));
		}

		return tickets;
	}
}
