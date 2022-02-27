package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
	FIRST(6, 2_000_000_000), SECOND(5, 30_000_000), THIRD(5, 1_500_000),
	FOURTH(4, 50_000), FIFTH(3, 5_000), NONE(0, 0);

	private static final int SECOND_AND_THIRD_BALL = 5;

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
		Rank rank = getMatchedRank(winningCount);
		if (isThirdRank(hasBonusBall, rank)) {
			return Rank.THIRD;
		}
		return rank;
	}

	private static Rank getMatchedRank(final int winningCount) {
		return Arrays.stream(values())
			.filter(rank -> rank.getCorrectedBalls() == winningCount)
			.findFirst()
			.orElse(Rank.NONE);
	}

	private static boolean isThirdRank(final boolean hasBonusBall, final Rank rank) {
		return rank.getCorrectedBalls() == SECOND_AND_THIRD_BALL && !hasBonusBall;
	}

	public static List<Rank> valuesWithoutNone() {
		return Arrays.stream(values())
			.filter(rank -> rank != NONE)
			.collect(Collectors.toList());
	}
}
