package domain;

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
		if (numOfManualLottery > this.numOfAutoLottery) {
			throw new IllegalArgumentException("수동 로또의 개수가 구입한 로또 총 개수보다 많을 수 없습니다.");
		}
		final int numOfAutoLottery = this.numOfAutoLottery - numOfManualLottery;
		return new NumOfLottery(numOfAutoLottery, numOfManualLottery);
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
