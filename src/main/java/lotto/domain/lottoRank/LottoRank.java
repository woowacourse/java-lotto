package lotto.domain.lottoRank;

import java.util.Arrays;

import lotto.domain.lottoMoney.LottoMoney;

public enum LottoRank {
	FIRST(new MatchCount(6), false, new LottoMoney(2_000_000_000)),
	SECOND(new MatchCount(5), true, new LottoMoney(30_000_000)),
	THIRD(new MatchCount(5), false, new LottoMoney(1_500_000)),
	FOURTH(new MatchCount(4), false, new LottoMoney(50_000)),
	FIFTH(new MatchCount(3), false, new LottoMoney(5_000)),
	MISS(new MatchCount(0), false, new LottoMoney(0));

	private MatchCount matchCount;
	private boolean hasBonusLottoNumber;
	private LottoMoney winningLottoMoney;

	LottoRank(MatchCount matchCount, boolean hasBonusLottoNumber, LottoMoney winningLottoMoney) {
		this.matchCount = matchCount;
		this.hasBonusLottoNumber = hasBonusLottoNumber;
		this.winningLottoMoney = winningLottoMoney;
	}

	public static LottoRank of(MatchCount matchCount, boolean hasBonusLottoNumber) {
		if (isSecond(matchCount, hasBonusLottoNumber)) {
			return LottoRank.SECOND;
		}
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.matchCount.equals(matchCount))
			.findFirst()
			.orElse(MISS);
	}

	private static boolean isSecond(MatchCount matchCount, boolean hasBonusLottoNumber) {
		if (SECOND.hasBonusLottoNumber != hasBonusLottoNumber) {
			return false;
		}
		return SECOND.matchCount.equals(matchCount);
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
