package domain;

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
}
