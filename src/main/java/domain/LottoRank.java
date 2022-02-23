package domain;

public enum LottoRank {

	FIRST(2000000000, 6, false),
	SECOND(30000000, 5, true),
	THIRD(1500000, 5, false),
	FOURTH(50000, 4, false),
	FIFTH(5000, 3, false),
	FAIL(0, -1, false);

	private final long amount;
	private final int matchCount;
	private final boolean hasBonusNumber;

	LottoRank(long amount, int matchCount, boolean hasBonusNumber) {
		this.amount = amount;
		this.matchCount = matchCount;
		this.hasBonusNumber = hasBonusNumber;
	}

	public static LottoRank findRank(int count, boolean bonusNumber) {
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
