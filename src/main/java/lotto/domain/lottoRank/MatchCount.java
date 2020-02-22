package lotto.domain.lottoRank;

import java.util.Arrays;

public enum MatchCount {
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	MISS(0);

	private final int matchCount;

	MatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public static MatchCount valueOf(int matchCount) {
		return Arrays.stream(values())
			.filter(value -> value.matchCount == matchCount)
			.findFirst()
			.orElse(MISS);
	}

	public int getMatchCount() {
		return matchCount;
	}
}
