package domain;

import java.util.List;

public class Lottery {

	public static final int LOTTERY_SIZE = 6;

	private static final String LOTTERY_SIZE_ERROR = LotteryNumber.LOTTERY_NUMBER + "는 "
		+ LOTTERY_SIZE + "개여야 합니다.";

	private final List<LotteryNumber> numbers;

	public Lottery(final List<LotteryNumber> numbers) {
		validateSize(numbers.size());
		this.numbers = numbers;
	}

	private void validateSize(final int size) {
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(LOTTERY_SIZE_ERROR);
		}
	}

	public int countSameNumber(final Lottery lottery) {
		return (int) numbers.stream()
			.filter(lottery::contains)
			.count();
	}

	public boolean contains(final LotteryNumber number) {
		return numbers.contains(number);
	}

	public List<LotteryNumber> getNumbers() {
		return numbers;
	}
}
