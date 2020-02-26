package lotto.domain;

import java.util.Arrays;

import lotto.domain.lotto.LottoMoney;

public enum LottoRank {
	FIRST(6, new LottoMoney(2_000_000_000)),
	SECOND(5, new LottoMoney(30_000_000)),
	THIRD(5, new LottoMoney(1_500_000)),
	FOURTH(4, new LottoMoney(50_000)),
	FIFTH(3, new LottoMoney(5_000)),
	MISS(0, new LottoMoney(0));

	private int matchCount;
	private LottoMoney winningMoney;

	LottoRank(int matchCount, LottoMoney winningMoney) {
		this.matchCount = matchCount;
		this.winningMoney = winningMoney;
	}

	public static LottoRank of(int matchCount, boolean hasBonusNumber) {
		if (hasBonusNumber && LottoRank.SECOND.matchCount == matchCount) {
			return LottoRank.SECOND;
		}

		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank != LottoRank.SECOND)
			.filter(lottoRank -> lottoRank.matchCount == matchCount)
			.findFirst()
			.orElse(MISS);
	}

	public boolean isLottoRankOf(LottoRank lottoRank) {
		return this == lottoRank;
	}

	public LottoMoney getWinningMoney() {
		return winningMoney;
	}

	public int getMatchCount() {
		return matchCount;
	}
}
