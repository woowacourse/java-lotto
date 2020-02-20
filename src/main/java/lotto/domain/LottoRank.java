package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoRank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000, (matchCount, hasBonus) -> matchCount == 5 && hasBonus),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000),
	MISS(0, 0);

	private final long matchCount;
	private final long winnings;
	private final BiPredicate<Long, Boolean> predicate;

	LottoRank(long matchCount, long winnings) {
		this(matchCount, winnings, (count, hasBonus) -> count == matchCount);
	}

	LottoRank(long matchCount, long winnings, BiPredicate<Long, Boolean> predicate) {
		this.matchCount = matchCount;
		this.winnings = winnings;
		this.predicate = predicate;
	}

	public static LottoRank of(long matchCount, boolean hasBonus) {
		return Arrays.stream(values())
				.filter(rank -> rank.predicate.test(matchCount, hasBonus))
				.findFirst()
				.orElse(MISS);
	}

	public long getWinnings() {
		return winnings;
	}

	public long getMatchCount() {
		return matchCount;
	}
}
