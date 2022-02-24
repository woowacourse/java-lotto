package lotto.domain.ticket.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomTicketGenerator implements TicketGenerator {

	private static final int TICKET_DEFAULT_SIZE = 6;

	private Iterator<Integer> numbers;

	public void initNumbers(final List<Integer> numbers) {
		this.numbers = numbers.iterator();
	}

	@Override
	public List<Integer> generate() {
		final List<Integer> ticketNumbers = new ArrayList<>();
		for (int i = 0; i < TICKET_DEFAULT_SIZE; i++) {
			ticketNumbers.add(numbers.next());
		}
		return ticketNumbers;
	}

}
