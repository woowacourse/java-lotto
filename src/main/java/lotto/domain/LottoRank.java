package lotto.domain;

import java.util.Arrays;
import java.util.function.Predicate;

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
		return Arrays.stream(values())
				.filter(getLottoRankPredicate(matchCount, hasBonus))
				.findFirst()
				.orElse(MISS);
	}

	private static Predicate<LottoRank> getLottoRankPredicate(long matchCount, boolean hasBonus) {
		return rank -> matchSecond(matchCount, hasBonus, rank) || match(matchCount, rank);
	}

	private static boolean match(long matchCount, LottoRank rank) {
		return rank != SECOND
				&& rank.matchCount == matchCount;
	}

	private static boolean matchSecond(long matchCount, boolean hasBonus, LottoRank rank) {
		return rank == SECOND && rank.matchCount == matchCount && hasBonus;
	}

	public long calculateTotalWinnings(long amount) {
		return winnings * amount;
	}
}
