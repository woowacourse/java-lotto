package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tickets {
	private final List<Ticket> tickets;

	public Tickets(final List<Ticket> tickets) {
		this.tickets = List.copyOf(tickets);
	}

	public List<Rank> getRanks(final WinningNumber winningNumber) {
		return tickets.stream()
			.map(ticket -> ticket.getRank(winningNumber))
			.filter(Objects::nonNull)
			.collect(Collectors.toUnmodifiableList());
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public int size() {
		return tickets.size();
	}
}
