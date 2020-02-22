package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public enum Rank {
	FIRST(6, 2000000000),
	SECOND(5, 30000000),
	THIRD(5, 1500000),
	FOURTH(4, 50000),
	FIFTH(3, 5000),
	NONE(0, 0);

	private static final int SECOND_OR_THIRD_MATCH = 5;

	private final int matchCount;
	private final int amount;

	Rank(final int matchCount, final int amount) {
		this.matchCount = matchCount;
		this.amount = amount;
	}

	public int getMatchCount() {
		return this.matchCount;
	}

	public int getAmount() {
		return this.amount;
	}

	public static Rank of(final int matchCount, final boolean isBonusNotMatch) {
		if (matchCount == SECOND_OR_THIRD_MATCH && isBonusNotMatch) {
			return THIRD;
		}
		return Stream.of(Rank.values())
			.filter(rank -> rank.matchCount == matchCount)
			.findFirst()
			.orElse(NONE);
	}

	public static Ranks getOrderReversed() {
		final Rank[] rankValues = Rank.values();
		List<Rank> rankResults = Arrays.asList(rankValues);
		Collections.reverse(rankResults);
		return new Ranks(rankResults);
	}
}
