package domain;

import java.util.List;

public class Tickets {
	private final List<Ticket> tickets;

	public Tickets(final List<Ticket> tickets) {
		this.tickets = List.copyOf(tickets);
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public int size() {
		return tickets.size();
	}
}
