package lotto.domain.result;

import java.util.Arrays;

import lotto.domain.money.Money;

public enum LottoRank {
	FIRST(6, new Money(2_000_000_000)),
	SECOND(5, new Money(30_000_000)),
	THIRD(5, new Money(1_500_000)),
	FOURTH(4, new Money(50_000)),
	FIFTH(3, new Money(5_000)),
	MISS(0, new Money(0));

	private int matchCount;
	private Money winningMoney;

	LottoRank(int matchCount, Money winningMoney) {
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

	public Money getWinningMoney() {
		return winningMoney;
	}

	public int getMatchCount() {
		return matchCount;
	}
}
