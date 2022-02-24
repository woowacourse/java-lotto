package domain.lottery;

import java.util.ArrayList;
import java.util.List;

public class Lottery {

	private final List<Integer> numbers;

	public Lottery(List<Integer> numbers) {
		validateNumbers(numbers);
		this.numbers = numbers;
	}


	private void validateNumbers(List<Integer> numbers) {
		validateSize(numbers.size());
		validateRange(numbers);
	}

	private void validateSize(int size) {
		if (size != 6) {
			throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
		}
	}

	private void validateRange(List<Integer> numbers) {
		numbers.forEach(this::checkNumberRange);
	}


	private void checkNumberRange(final int numbers) {
		if(1 > numbers || numbers > 45) {
			throw new IllegalArgumentException("로또의 각 번호는 1~45 사이여야 합니다");
		}
	}

	public int countSameNumber(final Lottery lottery) {
		final List<Integer> differences = new ArrayList<>(this.numbers);
		differences.removeAll(lottery.numbers);
		return 6 - differences.size();
	}

	public boolean contains(final int number) {
		return numbers.contains(number);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
