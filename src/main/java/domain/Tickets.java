package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.strategy.TicketGenerator;

public class Tickets {
	private static final int TICKET_PRICE = 1000;

	private final List<Ticket> tickets;

	public Tickets() {
		this.tickets = new ArrayList<>();
	}

	public void makeTickets(int payment, TicketGenerator ticketGenerator) {
		for (int i = 0; i < payment / TICKET_PRICE; i++) {
			tickets.add(new Ticket(ticketGenerator.generate()));
		}
	}

	public List<Rank> getRanks(WinningNumber winningNumber) {
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		tickets.forEach(ticket -> stringBuilder.append(ticket.toString()));

		return stringBuilder.toString();
	}
}
