package domain;

import java.util.ArrayList;
import java.util.List;

import utils.LotteryMessage;

public class Lottery {

	private static final int LOTTERY_SIZE = 6;
	private static final int LOTTERY_NUMBER_MIN_RANGE = 1;
	private static final int LOTTERY_NUMBER_MAX_RANGE = 45;

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
			throw new IllegalArgumentException(LotteryMessage.LOTTERY_SIZE_ERROR);
		}
	}

	private void validateRange(List<Integer> numbers) {
		numbers.forEach(this::checkNumberRange);
	}


	private void checkNumberRange(final int numbers) {
		if (LOTTERY_NUMBER_MIN_RANGE > numbers || numbers > LOTTERY_NUMBER_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.LOTTERY_RANGE_ERROR);
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
