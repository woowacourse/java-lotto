package lotto.domain;

public enum LottoRank {
	FIFTH(3, false, 5000),
	FOURTH(4, false, 50000),
	THIRD(5, false, 1_500_000),
	SECOND(5, true, 30_000_000),
	FIRST(6, false, 2_000_000_000);

	private final int matchCount;
	private final boolean bonusBall;
	private final int prize;

	LottoRank(int matchCount, boolean bonusBall, int prize) {
		this.matchCount = matchCount;
		this.bonusBall = bonusBall;
		this.prize = prize;
	}
}
