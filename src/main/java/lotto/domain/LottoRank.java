package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(3, false, 5000),
	FOURTH(4, false, 50000),
	THIRD(5, false, 1_500_000),
	SECOND(5, true, 30_000_000),
	FIRST(6, false, 2_000_000_000);

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";
	private final int matchCount;
	private final boolean bonusBall;
	private final int prize;

	LottoRank(int matchCount, boolean bonusBall, int prize) {
		this.matchCount = matchCount;
		this.bonusBall = bonusBall;
		this.prize = prize;
	}

	public static LottoRank getRank(int matchCount) {
		LottoRank[] values = values();
		for (LottoRank value : values) {
			if (value.matchCount == matchCount) {
				return value;
			}
		}
		throw new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE);
	}

	public long getTotal(int count) {
		return (long) count * (long) prize;
	}
}
