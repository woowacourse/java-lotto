package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.ArrayList;
import java.util.List;

public class Lottery {

	private static final int LOTTERY_SIZE = 6;

	private final List<LotteryNumber> numbers;

	private Lottery(final List<LotteryNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = numbers;
	}

	public static Lottery from(final List<LotteryNumber> numbers) {
		return new Lottery(numbers);
	}

	private void validateNumbers(List<LotteryNumber> numbers) {
		validateSize(numbers.size());
	}

	private void validateSize(int size) {
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION.getMessage());
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
