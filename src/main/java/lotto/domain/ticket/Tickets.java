package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.ball.Ball;
import lotto.domain.ball.Balls;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.TicketGenerator;

public class Tickets {

	private final TicketGenerator ticketGenerator;
	private final List<Ticket> tickets;

	public Tickets(int count, TicketGenerator ticketGenerator) {
		this.ticketGenerator = ticketGenerator;
		this.tickets = generateTickets(count);
	}

	private List<Ticket> generateTickets(int count) {
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			tickets.add(new Ticket(ticketGenerator));
		}
		return tickets;
	}

	public List<Rank> getRanks(Balls answer, Ball bonusBall) {
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
