package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, LottoMoney.FIRST_PRIZE),
	SECOND(5, LottoMoney.SECOND_PRIZE),
	THIRD(5, LottoMoney.THIRD_PRIZE),
	FOURTH(4, LottoMoney.FOURTH_PRIZE),
	FIFTH(3, LottoMoney.FIFTH_PRIZE),
	MISS(0, LottoMoney.MISS_PRIZE);

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
