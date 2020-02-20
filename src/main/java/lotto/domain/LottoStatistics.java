package lotto.domain;

import java.util.Arrays;

public class LottoStatistics {
	private final LottoPurchaseMoney lottoPurchaseMoney;
	private final LottoResults lottoResults;

	public LottoStatistics(LottoPurchaseMoney lottoPurchaseMoney, LottoResults lottoResults) {
		this.lottoPurchaseMoney = lottoPurchaseMoney;
		this.lottoResults = lottoResults;
	}

	public long getProfitRate() {
		return sumWinnings() / lottoPurchaseMoney.getValue() * 100;
	}

	private long sumWinnings() {
		return Arrays.stream(LottoRank.values())
				.mapToLong(rank -> rank.getWinnings() * lottoResults.getRankCount(rank))
				.sum();
	}
}
