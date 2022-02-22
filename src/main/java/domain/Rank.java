package domain;

public enum Rank {
	FIRST_GRADE(6, false, 3_000_000_000L),
	SECOND_GRADE(5, true, 30_000_000L),
	THIRD_GRADE(5, false, 1_500_000L),
	FOURTH_GRADE(4, false, 50_000L),
	FIFTH_GRADE(3, false, 5_000L);

	private final int matchCount;
	private final boolean bonusBallMatched;
	private final Long prize;

	Rank(final int matchCount, final boolean bonusBallMatched, final Long prize) {
		this.matchCount = matchCount;
		this.bonusBallMatched = bonusBallMatched;
		this.prize = prize;
	}
}
