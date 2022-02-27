package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.Objects;

public class LotteryNumber implements Comparable<LotteryNumber>{

	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;

	private int number;

	public LotteryNumber(final int number) {
		validateNumber(number);
		this.number = number;
	}

	private void validateNumber(final int number) {
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
