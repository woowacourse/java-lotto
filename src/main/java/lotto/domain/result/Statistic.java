package lotto.domain.result;

import java.util.Arrays;
import java.util.Optional;

public enum Statistic {
	THREE(3, 5000),
	FOUR(4, 50_000),
	FIVE(5, 1_500_000),
	BONUS(5, 3_000_000),
	SIX(6, 2_000_000_000);

	private final int matchingNumbers;
	private final double prize;

	Statistic(int matchingNumbers, double prize) {
		this.matchingNumbers = matchingNumbers;
		this.prize = prize;
	}

	public static Optional<Statistic> getRank(int numberOfMatch) {
		return Arrays.stream(values())
			.filter(statistic -> statistic.matchingNumbers == numberOfMatch)
			.findFirst();
	}

	public int getMatchingNumbers() {
		return matchingNumbers;
	}

	public double getPrize() {
		return prize;
	}
}
