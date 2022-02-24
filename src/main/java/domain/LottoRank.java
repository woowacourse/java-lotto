package domain;

public enum LottoRank {

	FAIL(0, -1, false),
	FIFTH(5000, 3, false),
	FOURTH(50000, 4, false),
	THIRD(1500000, 5, false),
	SECOND(30000000, 5, true),
	FIRST(2000000000, 6, false);

	private final long amount;
	private final int matchCount;
	private final boolean hasBonusNumber;

	LottoRank(final long amount, final int matchCount, final boolean hasBonusNumber) {
		this.amount = amount;
		this.matchCount = matchCount;
		this.hasBonusNumber = hasBonusNumber;
	}

	public static LottoRank findRank(final int count, final boolean bonusNumber) {
		for (LottoRank rank : LottoRank.values()) {
			if (rank.getMatchCount() == count && rank.isHasBonusNumber() == bonusNumber) {
				return rank;
			}
		}
		return FAIL;
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
