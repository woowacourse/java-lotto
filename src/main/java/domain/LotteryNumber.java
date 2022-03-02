package domain;

import java.util.Objects;

import utils.LotteryMessage;

public class LotteryNumber implements Comparable<LotteryNumber> {

	public static final int NUMBER_MIN_RANGE = 1;
	public static final int NUMBER_MAX_RANGE = 45;
	private final int lotteryNumber;

	public LotteryNumber(final int lotteryNumber) {
		checkNumberRange(lotteryNumber);
		this.lotteryNumber = lotteryNumber;
	}

	private void checkNumberRange(final int lotteryNumbers) {
		if (isOutOfRange(lotteryNumbers)) {
			throw new IllegalArgumentException(LotteryMessage.LOTTERY_RANGE_ERROR);
		}
	}

	private boolean isOutOfRange(final int lotteryNumbers) {
		return NUMBER_MIN_RANGE > lotteryNumbers || lotteryNumbers > NUMBER_MAX_RANGE;
	}

	public int getLotteryNumber() {
		return lotteryNumber;
	}

	@Override
	public int compareTo(LotteryNumber o) {
		return this.lotteryNumber - o.lotteryNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof LotteryNumber)) {
			return false;
		}
		return ((LotteryNumber)obj).lotteryNumber == this.lotteryNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lotteryNumber);
	}
}
