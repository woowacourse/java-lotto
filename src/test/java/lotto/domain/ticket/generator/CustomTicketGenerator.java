package lotto.domain.ticket.generator;

import java.util.Iterator;
import java.util.List;

public class CustomTicketGenerator implements TicketGenerator {

	private Iterator<TicketNumbers> ticketIterator;

	public void initNumbers(final List<TicketNumbers> tickets) {
		this.ticketIterator = tickets.iterator();
	}

	@Override
	public List<Integer> generate() {
		final TicketNumbers ticketNumbers = ticketIterator.next();
		return ticketNumbers.getNumbers();
	}

}
