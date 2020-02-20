package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000),
	MISS(0, 0);

	private final long matchCount;
	private final long winnings;

	LottoRank(long matchCount, long winnings) {
		this.matchCount = matchCount;
		this.winnings = winnings;
	}

	public static LottoRank of(long matchCount, boolean hasBonus) {
		if (isThird(matchCount, hasBonus)) {
			return THIRD;
		}
		return Arrays.stream(values())
				.filter(rank -> rank.matchCount == matchCount)
				.findFirst()
				.orElse(MISS);
	}

	private static boolean isThird(long matchCount, boolean hasBonus) {
		return matchCount == THIRD.matchCount && !hasBonus;
	}

	public long getWinnings() {
		return winnings;
	}
}
