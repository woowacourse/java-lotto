package model.result;

import java.util.Arrays;

public enum Rank {
	FAIL(0, 0),
	FIFTH(3, 5000),
	FOURTH(4, 50000),
	THIRD(5, 1500000),
	SECOND(5, 30000000),
	FIRST(6, 2000000000);

	private static final int REWARD_NUMBER = 3;
	private static final int BONUS_NUMBER = 5;

	private final int matchNumber;
	private final int value;

	Rank(int matchNumber, int value) {
		this.matchNumber = matchNumber;
		this.value = value;
	}

	public boolean checkNumberToReward() {
		return this.matchNumber >= REWARD_NUMBER;
	}

	public static Rank getRank(long matchNumber, boolean matchBonus) {
		if (matchNumber == BONUS_NUMBER && matchBonus) {
			return SECOND;
		}

		return Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() == matchNumber)
			.findAny()
			.orElse(FAIL);
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public int getValue() {
		return value;
	}
}
