package domain;

import java.util.ArrayList;
import java.util.List;

import utils.LotteryMessage;

public class Lottery {

	private static final int LOTTERY_SIZE = 6;
	private final List<LotteryNumber> numbers;

	public Lottery(final List<LotteryNumber> numbers) {
		validateSize(numbers.size());
		this.numbers = numbers;
	}


	private void validateSize(final int size) {
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(LotteryMessage.LOTTERY_SIZE_ERROR);
		}
	}

	public int countSameNumber(final Lottery lottery) {
		final List<LotteryNumber> differences = new ArrayList<>(this.numbers);
		differences.removeAll(lottery.numbers);
		return LOTTERY_SIZE - differences.size();
	}

	public boolean contains(final LotteryNumber number) {
		return numbers.contains(number);
	}

	public List<LotteryNumber> getNumbers() {
		return numbers;
	}
}
