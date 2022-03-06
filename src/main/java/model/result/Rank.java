package model.result;

import java.util.Arrays;

public enum Rank {
	FAIL(0, 0),
	FIFTH(3, 5_000),
	FOURTH(4, 50_000),
	THIRD(5, 1_500_000),
	SECOND(5, 30_000_000),
	FIRST(6, 2_000_000_000);

	private static final int STANDARD_VALUE_OF_REWARD = 0;
	private static final int BONUS_NUMBER = 5;

	private final int matchNumber;
	private final int value;

	Rank(int matchNumber, int value) {
		this.matchNumber = matchNumber;
		this.value = value;
	}

	public boolean hasReward() {
		return this.value > STANDARD_VALUE_OF_REWARD;
	}

	public static Rank getRank(long matchNumber, boolean matchBonus) {
		if (matchNumber == BONUS_NUMBER) {
			return getRankWithCheckingBonus(matchBonus);
		}

		return Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() == matchNumber)
			.findFirst()
			.orElse(FAIL);
	}

	private static Rank getRankWithCheckingBonus(boolean matchBonus) {
		if (matchBonus) {
			return SECOND;
		}
		return THIRD;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public int getValue() {
		return value;
	}
}
