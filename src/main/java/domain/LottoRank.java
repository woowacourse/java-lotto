package domain;

import java.util.Arrays;
import java.util.EnumMap;

public enum LottoRank {

	FAIL(0, -1, false),
	FIFTH(5_000, 3, false),
	FOURTH(50_000, 4, false),
	THIRD(1_500_000, 5, false),
	SECOND(30_000_000, 5, true),
	FIRST(2_000_000_000, 6, false);

	private static final int INITIAL_COUNT = 0;

	private final long amount;
	private final int matchCount;
	private final boolean hasBonusNumber;

	LottoRank(final long amount, final int matchCount, final boolean hasBonusNumber) {
		this.amount = amount;
		this.matchCount = matchCount;
		this.hasBonusNumber = hasBonusNumber;
	}

	public static LottoRank findRank(final int count, final boolean bonusNumber) {
		for (LottoRank rank : values()) {
			if (rank.getMatchCount() == count && canSecond(bonusNumber, rank)) {
				return rank;
			}
		}
		return FAIL;
	}

	private static boolean canSecond(boolean bonusNumber, LottoRank rank) {
		if (rank == SECOND || rank == THIRD)
			return rank.isHasBonusNumber() == bonusNumber;
		return true;
	}

	public static EnumMap<LottoRank, Integer> createWinningResultMap() {
		EnumMap<LottoRank, Integer> rankMap = new EnumMap<>(LottoRank.class);
		Arrays.stream(values())
			.filter(value -> !isFail(value))
			.forEach(value -> rankMap.put(value, INITIAL_COUNT));
		return rankMap;
	}

	public static boolean isFail(final LottoRank rank) {
		return rank == FAIL;
	}

	public long getAmount() {
		return amount;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean isHasBonusNumber() {
		return hasBonusNumber;
	}

}
