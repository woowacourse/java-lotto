package lotto.domain.ticket;

import java.util.Iterator;
import java.util.List;

import lotto.domain.ticket.generator.TicketGenerator;

class CustomTicketGenerator implements TicketGenerator {

	private Iterator<List<Integer>> numbers;

	public void initNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers.iterator();
	}

	@Override
	public List<Integer> generate() {
		return this.numbers.next();
	}

}
