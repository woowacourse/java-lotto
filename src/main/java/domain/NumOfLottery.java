package domain;

import static domain.exception.LotteryExceptionMessages.*;

public final class NumOfLottery {

	private final int numOfAutoLottery;
	private final int numOfManualLottery;

	private NumOfLottery(final int numOfAutoLottery) {
		this.numOfAutoLottery = numOfAutoLottery;
		this.numOfManualLottery = 0;
	}

	private NumOfLottery(final int numOfAutoLottery, final int numOfManualLottery) {
		this.numOfAutoLottery = numOfAutoLottery;
		this.numOfManualLottery = numOfManualLottery;
	}

	public static NumOfLottery from(final int numOfAutoLottery) {
		return new NumOfLottery(numOfAutoLottery);
	}

	public NumOfLottery putNumOfManualLottery(final int numOfManualLottery) {
		validateBound(numOfManualLottery);
		final int numOfAutoLottery = this.numOfAutoLottery - numOfManualLottery;
		return new NumOfLottery(numOfAutoLottery, numOfManualLottery);
	}

	private void validateBound(final int numOfManualLottery) {
		if (numOfManualLottery > this.numOfAutoLottery) {
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
