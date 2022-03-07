package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public final class LotteryNumber implements Comparable<LotteryNumber> {

	public static final int MIN_LOTTERY_NUMBER = 1;
	public static final int MAX_LOTTERY_NUMBER = 45;

	private static Map<Integer, LotteryNumber> lotteryNumbers = new HashMap<>();

	static {
		IntStream.rangeClosed(MIN_LOTTERY_NUMBER, MAX_LOTTERY_NUMBER)
			.forEach(number -> lotteryNumbers.put(number, new LotteryNumber(number)));
	}

	private final int number;

	private LotteryNumber(final int number) {
		this.number = number;
	}

	public static LotteryNumber from(final int number) {
		validateNumber(number);
		return lotteryNumbers.get(number);
	}

	private static void validateNumber(final int number) {
		if (number < MIN_LOTTERY_NUMBER || number > MAX_LOTTERY_NUMBER) {
			throw new IllegalArgumentException(INVALID_RANGE_EXCEPTION.getMessage());
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LotteryNumber that = (LotteryNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public int compareTo(LotteryNumber o) {
		return Integer.compare(this.number, o.number);
	}
}
