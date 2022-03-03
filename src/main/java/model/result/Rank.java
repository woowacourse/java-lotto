package model.result;

public enum Rank {
	FIRST(6, 2000000000),
	SECOND(5, 30000000),
	THIRD(5, 1500000),
	FOURTH(4, 50000),
	FIFTH(3, 5000),
	FAIL(0, 0);

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
