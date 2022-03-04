package model.result;

public enum Rank {
	FAIL(0, 0),
	FIFTH(3, 5000),
	FOURTH(4, 50000),
	THIRD(5, 1500000),
	SECOND(5, 30000000),
	FIRST(6, 2000000000);

	private static final int REWARD_NUMBER = 3;

	private final int matchNumber;
	private final int value;

	Rank(int matchNumber, int value) {
		this.matchNumber = matchNumber;
		this.value = value;
	}

	public boolean checkNumberToReward() {
		return this.matchNumber >= REWARD_NUMBER;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public int getValue() {
		return value;
	}
}
