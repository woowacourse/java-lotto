package domain;

import utils.LotteryMessage;

public class LotteryNumber implements Comparable<LotteryNumber> {

	private static final int NUMBER_MIN_RANGE = 1;
	private static final int NUMBER_MAX_RANGE = 45;
	private final int lotteryNumber;

	public LotteryNumber(final int lotteryNumber) {
		checkNumberRange(lotteryNumber);
		this.lotteryNumber = lotteryNumber;
	}

	private void checkNumberRange(final int lotteryNumbers) {
		if (NUMBER_MIN_RANGE > lotteryNumbers || lotteryNumbers > NUMBER_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.LOTTERY_RANGE_ERROR);
		}
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

}
