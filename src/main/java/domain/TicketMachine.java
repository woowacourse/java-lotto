package domain;

import java.util.ArrayList;
import java.util.List;

import domain.strategy.TicketingStrategy;

public class TicketMachine {

	public static Tickets generateTickets(final int payment, final TicketingStrategy ticketingStrategy) {
		final List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < payment / Ticket.PRICE; i++) {
			tickets.add(new Ticket(ticketingStrategy.generate()));
		}

		return new Tickets(tickets);
	}
	// 수동으로 구매하고 남은 돈으로 자동으로 구매한다.

}
