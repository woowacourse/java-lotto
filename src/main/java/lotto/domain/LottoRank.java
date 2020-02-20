package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIFTH(new MatchCount(3), new Prize(5000)),
	FOURTH(new MatchCount(4), new Prize(50000)),
	THIRD(new MatchCount(5), new Prize(1_500_000)),
	SECOND(new MatchCount(5), new Prize(30_000_000)),
	FIRST(new MatchCount(6), new Prize(2_000_000_000));

	private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

	private final MatchCount matchCount;
	private final Prize prize;

	LottoRank(MatchCount matchCount, Prize prize) {
		this.matchCount = matchCount;
		this.prize = prize;
	}

	public static LottoRank getRank(int matchCount) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.matchCount.getMatchCount() == matchCount)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE));
	}

	public static boolean isPrizeCount(int matchCount) {
		return Arrays.stream(values())
			.anyMatch(rank -> rank.matchCount.getMatchCount() == matchCount);
	}

	public long getTotal(int count) {
		return this.prize.multiply(count);
	}

	public int getMatchCount() {
		return this.matchCount.getMatchCount();
	}

	public long getPrize() {
		return this.prize.getPrize();
	}
}
