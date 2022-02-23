package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.TicketGenerator;

public class Tickets {

	private final TicketGenerator ticketGenerator;
	private final List<Ticket> tickets;

	public Tickets(final int count, final TicketGenerator ticketGenerator) {
		this.ticketGenerator = ticketGenerator;
		this.tickets = generateTickets(count);
	}

	private List<Ticket> generateTickets(final int count) {
		final List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			final List<Integer> numbers = ticketGenerator.generate();
			final Ticket ticket = new Ticket(numbers);
			tickets.add(ticket);
		}
		return tickets;
	}

	public List<Rank> getRanks(final Ticket answer, final Ball bonusBall) {
		return tickets.stream()
			.map(ticket -> ticket.getRank(answer, bonusBall))
			.filter(Objects::nonNull)
			.collect(Collectors.toUnmodifiableList());
	}

	public int getSize() {
		return tickets.size();
	}

	public List<Ticket> getTickets() {
		return new ArrayList<>(tickets);
	}

}
