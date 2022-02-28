package domain.strategy;

import java.util.Iterator;
import java.util.List;

public class CustomTicketGenerateStrategy implements TicketGenerateStrategy {

	private Iterator<List<Integer>> numbers;

	public void initNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers.iterator();
	}

	@Override
	public List<Integer> generate() {
		return this.numbers.next();
	}
}
