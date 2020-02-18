package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000),
	MISS(0, 0);

	private final int matchCount;
	private final int winnings;

	LottoRank(int matchCount, int winnings) {
		this.matchCount = matchCount;
		this.winnings = winnings;
	}

	public static LottoRank of(int matchCount, boolean isBonus) {
		if (isMismatch(matchCount))
			return MISS;
		if (SECOND.match(matchCount) && isBonus)
			return SECOND;
		return Arrays.stream(values())
				.filter(rank -> rank != SECOND && rank.match(matchCount))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 순위 입니다."));
	}

	private boolean match(int matchCount) {
		return this.matchCount == matchCount;
	}

	private static boolean isMismatch(int matchCount) {
		return matchCount >= MISS.matchCount && matchCount < FIFTH.matchCount;
	}
}
