package domain;

import java.util.Arrays;

public enum Rank {
	FIRST(6, 2_000_000_000), SECOND(5, 30_000_000), THIRD(5, 1_500_000),
	FOURTH(4, 50_000), FIFTH(3, 5_000), NONE(1, 0);

	private final int correctedBalls;
	private final int prize;

	Rank(final int correctedBalls, final int prize) {
		this.correctedBalls = correctedBalls;
		this.prize = prize;
	}

	public int getCorrectedBalls() {
		return correctedBalls;
	}

	public int getPrize() {
		return prize;
	}

	public static Rank getRank(final int winningCount, boolean hasBonusBall) {
		Rank properRank = Arrays.stream(values())
			.filter(rank -> rank.getCorrectedBalls() == winningCount)
			.findFirst()
			.orElse(Rank.NONE);

		if (properRank.getCorrectedBalls() == 5 && !hasBonusBall) {
			return Rank.THIRD;
		}
		return properRank;
	}
}
