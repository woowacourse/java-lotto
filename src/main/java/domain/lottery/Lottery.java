package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.ArrayList;
import java.util.List;

public class Lottery {

	private static final int LOTTERY_SIZE = 6;
	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;

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
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION.getMessage());
		}
	}

	private void validateRange(List<Integer> numbers) {
		numbers.forEach(this::checkNumberRange);
	}

	private void checkNumberRange(final int numbers) {
		if (MIN_LOTTERY_NUMBER > numbers || numbers > MAX_LOTTERY_NUMBER) {
			throw new IllegalArgumentException(INVALID_RANGE_EXCEPTION.getMessage());
		}
	}

	public int countSameNumber(final Lottery lottery) {
		final List<Integer> differences = new ArrayList<>(this.numbers);
		differences.removeAll(lottery.numbers);
		return LOTTERY_SIZE - differences.size();
	}

	public boolean contains(final int number) {
		return numbers.contains(number);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
