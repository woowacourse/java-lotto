package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.TicketGenerator;

public class Tickets {

	private final List<Ticket> tickets;

	public Tickets(final int count, final TicketGenerator ticketGenerator) {
		this.tickets = generateTickets(count, ticketGenerator);
	}

	private List<Ticket> generateTickets(final int count, final TicketGenerator ticketGenerator) {
		final List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			final List<Integer> numbers = ticketGenerator.generate();
			final Ticket ticket = new Ticket(numbers);
			tickets.add(ticket);
		}
		return tickets;
	}

	public List<Rank> calculateRanks(final Ticket answer, final Ball bonusBall) {
		return tickets.stream()
				.map(ticket -> ticket.calculateRank(answer, bonusBall))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toUnmodifiableList());
	}

	public int getTicketsCount() {
		return tickets.size();
	}

	public List<Ticket> getTickets() {
		return new ArrayList<>(tickets);
	}

}
