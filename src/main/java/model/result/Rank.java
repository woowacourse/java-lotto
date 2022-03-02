package model.result;

public enum Rank {
	ZERO(0, 0),
	ONE(1, 0),
	TWO(2, 0),
	THREE(3, 5000),
	FOUR(4, 50000),
	FIVE(5, 1500000),
	BONUS(5, 30000000),
	SIX(6, 2000000000);

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
