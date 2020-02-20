package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(3, 5000),
	FOURTH(4, 50000),
	THIRD(5, 1_500_000),
	SECOND(5, 30_000_000),
	FIRST(6, 2_000_000_000);

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

	private final int matchCount;
	private final long prize;

	LottoRank(int matchCount, long prize) {
		this.matchCount = matchCount;
		this.prize = prize;
	}

	public static LottoRank getRank(int matchCount) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.matchCount == matchCount)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE));
	}

	public static boolean isPrizeCount(int matchCount) {
		return Arrays.stream(values())
			.anyMatch(rank -> rank.matchCount == matchCount);
	}

	public long getTotal(int count) {
		return this.prize * count;
	}

	public int getMatchCount() {
		return this.matchCount;
	}

	public long getPrize() {
		return this.prize;
	}
}
