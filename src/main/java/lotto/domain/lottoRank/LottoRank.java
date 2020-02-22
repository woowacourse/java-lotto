package lotto.domain.lottoRank;

import java.util.Arrays;

import lotto.domain.lottoMoney.LottoMoney;

public enum LottoRank {
	FIRST(MatchCount.SIX, new LottoMoney(2_000_000_000)),
	SECOND(MatchCount.FIVE, new LottoMoney(30_000_000)),
	THIRD(MatchCount.FIVE, new LottoMoney(1_500_000)),
	FOURTH(MatchCount.FOUR, new LottoMoney(50_000)),
	FIFTH(MatchCount.THREE, new LottoMoney(5_000)),
	MISS(MatchCount.MISS, new LottoMoney(0));

	private MatchCount matchCount;
	private LottoMoney winningLottoMoney;

	LottoRank(MatchCount matchCount, LottoMoney winningLottoMoney) {
		this.matchCount = matchCount;
		this.winningLottoMoney = winningLottoMoney;
	}

	public static LottoRank of(MatchCount matchCount, boolean hasBonusLottoNumber) {
		if (hasBonusLottoNumber && SECOND.matchCount.equals(matchCount)) {
			return LottoRank.SECOND;
		}

		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.matchCount.equals(matchCount))
			.findFirst()
			.orElse(MISS);
	}

	public LottoMoney calculateWinningLottoMoneyBy(long lottoRankCount) {
		return this.winningLottoMoney.multiplyBy(lottoRankCount);
	}

	public int getMatchCount() {
		return matchCount.getMatchCount();
	}

	public long getWinningLottoMoney() {
		return winningLottoMoney.getLottoMoney();
	}
}
