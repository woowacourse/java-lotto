package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, LottoMoney.FIRST_RANK),
	SECOND(5, LottoMoney.SECOND_RANK),
	THIRD(5, LottoMoney.THIRD_RANK),
	FOURTH(4, LottoMoney.FOURTH_RANK),
	FIFTH(3, LottoMoney.FIFTH_RANK);

	private int matchCount;
	private LottoMoney winningMoney;

	LottoRank(int matchCount, LottoMoney winningMoney) {
		this.matchCount = matchCount;
		this.winningMoney = winningMoney;
	}

	public static LottoMoney getWinningMoney(int matchCount, boolean hasBonusNumber) {
		if (hasBonusNumber && LottoRank.SECOND.matchCount == matchCount) {
			return LottoRank.SECOND.winningMoney;
		}

		return Arrays.stream(LottoRank.values())
			.filter(lottoRank -> lottoRank.matchCount == matchCount)
			.map(lottoRank -> lottoRank.winningMoney)
			.findFirst()
			.orElse(LottoMoney.ZERO_MONEY);
	}
}
