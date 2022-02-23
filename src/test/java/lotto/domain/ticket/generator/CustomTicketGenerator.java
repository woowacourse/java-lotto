package lotto.domain.ticket.generator;

import java.util.Iterator;
import java.util.List;

public class CustomTicketGenerator implements TicketGenerator {

	private Iterator<List<Integer>> numbers;

	public void initNumbers(final List<List<Integer>> numbers) {
		this.numbers = numbers.iterator();
	}

	@Override
	public List<Integer> generate() {
		return this.numbers.next();
	}

}
