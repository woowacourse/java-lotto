package domain;

import static domain.exception.LotteryExceptionMessages.*;

public final class NumOfLottery {

	private final int numOfAutoLottery;
	private final int numOfManualLottery;

	private NumOfLottery(final int numOfAutoLottery, final int numOfManualLottery) {
		this.numOfAutoLottery = numOfAutoLottery;
		this.numOfManualLottery = numOfManualLottery;
	}

	public static NumOfLottery of(final int numOfTotalLottery, final int numOfManualLottery) {
		validateBound(numOfTotalLottery, numOfManualLottery);
		final int numOfAutoLottery = numOfTotalLottery - numOfManualLottery;
		return new NumOfLottery(numOfAutoLottery, numOfManualLottery);
	}

	private static void validateBound(int numOfTotalLottery, int numOfManualLottery) {
		if (numOfManualLottery > numOfTotalLottery) {
			throw new IllegalArgumentException(NUM_OF_MANUAL_LOTTERY_EXCEPTION.getMessage());
		}
	}

	public int getNumOfAutoLottery() {
		return this.numOfAutoLottery;
	}

	public int getNumOfManualLottery() {
		return this.numOfManualLottery;
	}

	public int getNumOfTotalLottery() {
		return this.numOfAutoLottery + this.numOfManualLottery;
	}
}
