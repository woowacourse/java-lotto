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

	public static LottoRank of(long matchCount, boolean isBonus) {
		if (isMismatch(matchCount))
			return MISS;
		if (SECOND.match(matchCount) && isBonus)
			return SECOND;
		return Arrays.stream(values())
				.filter(rank -> rank != SECOND && rank.match(matchCount))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 순위 입니다."));
	}

	private boolean match(long matchCount) {
		return this.matchCount == matchCount;
	}

	private static boolean isMismatch(long matchCount) {
		return matchCount >= MISS.matchCount && matchCount < FIFTH.matchCount;
	}

	public long calculateTotalWinnings(long amount) {
		return winnings * amount;
	}
}
