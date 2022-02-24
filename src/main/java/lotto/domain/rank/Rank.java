package lotto.domain.rank;

import java.util.Arrays;

public enum Rank {

	FIFTH_GRADE(3, false, 5_000L),
	FOURTH_GRADE(4, false, 50_000L),
	THIRD_GRADE(5, false, 1_500_000L),
	SECOND_GRADE(5, true, 30_000_000L),
	FIRST_GRADE(6, false, 3_000_000_000L);

	private final int matchCount;
	private final boolean bonusMatched;
	private final long prizeMoney;

	Rank(final int matchCount, final boolean bonusMatched, final long prizeMoney) {
		this.matchCount = matchCount;
		this.bonusMatched = bonusMatched;
		this.prizeMoney = prizeMoney;
	}

	public static Rank of(final int matchCount, final boolean bonusMatched) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.matches(matchCount, bonusMatched))
			.findFirst()
			.orElseGet(() -> null);
	}

	private boolean matches(final int matchCount, final boolean bonusMatched) {
		return (matchCount == this.matchCount) && (bonusMatched == this.bonusMatched);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean getBonusMatched() {
		return bonusMatched;
	}

	public long getPrizeMoney() {
		return prizeMoney;
	}

}
