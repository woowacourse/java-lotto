package lotto.domain.result;

import java.util.Arrays;

public enum Statistic {
	NOTHING(0, 0),
	THREE(3, 5000),
	FOUR(4, 50_000),
	FIVE(5, 1_500_000),
	BONUS(5, 3_000_000),
	SIX(6, 2_000_000_000);

	public static final int BONUS_MATCH = 5;
	private final int matchingNumbers;
	private final int prize;

	Statistic(int matchingNumbers, int prize) {
		this.matchingNumbers = matchingNumbers;
		this.prize = prize;
	}

	public static Statistic getRank(int numberOfMatch, boolean isBonus) {
		Statistic rank = Arrays.stream(values())
			.filter(statistic -> statistic.matchingNumbers == numberOfMatch)
			.findFirst()
			.orElse(NOTHING);
		if (isSecond(isBonus, rank))
			return Statistic.BONUS;
		return rank;
	}

	private static boolean isSecond(boolean isBonus, Statistic rank) {
		if (rank.matchingNumbers == BONUS_MATCH && isBonus) {
			return true;
		}
		return false;
	}

	public int getMatchingNumbers() {
		return matchingNumbers;
	}

	public int getPrize() {
		return prize;
	}
}
