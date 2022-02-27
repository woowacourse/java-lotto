package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.strategy.TicketGenerateStrategy;

public class Tickets {
	private static final String PAYMENT_ERROR = "구입 금액은 1000원 미만일 수 없습니다.";

	private final List<Ticket> tickets;

	public Tickets() {
		this.tickets = new ArrayList<>();
	}

	public void makeTickets(int payment, TicketGenerateStrategy ticketGenerateStrategy) {
		validatePayment(payment);

		for (int i = 0; i < payment / Ticket.PRICE; i++) {
			tickets.add(new Ticket(ticketGenerateStrategy.generate()));
		}
	}

	private void validatePayment(int payment) {
		if (payment < Ticket.PRICE) {
			throw new IllegalArgumentException(PAYMENT_ERROR);
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
}
