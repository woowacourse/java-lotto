package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.Collections;
import java.util.Set;

public final class Lottery {

	private static final int LOTTERY_SIZE = 6;

	private final Set<LotteryNumber> numbers;

	private Lottery(final Set<LotteryNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = numbers;
	}

	public static Lottery from(final Set<LotteryNumber> numbers) {
		return new Lottery(numbers);
	}

	private void validateNumbers(final Set<LotteryNumber> numbers) {
		validateSize(numbers.size());
	}

	private void validateSize(final int size) {
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION.getMessage());
		}
	}

	public int countSameNumber(final Lottery lottery) {
		return (int)lottery.numbers.stream()
			.filter(this::contains)
			.count();
	}

	public boolean contains(final LotteryNumber number) {
		return numbers.contains(number);
	}

	public Set<LotteryNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	public boolean isDuplicated(LotteryNumber number) {
		return this.numbers.stream()
			.anyMatch(lotteryNumber -> lotteryNumber.equals(number));
	}
}
