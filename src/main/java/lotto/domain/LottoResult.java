package lotto.domain;

import java.util.Map;

public class LottoResult {
	private final Map<LottoRank, Integer> rankResult;

	public LottoResult(Map<LottoRank, Integer> rankResult) {
		this.rankResult = rankResult;
	}

	public long calculateTotalPrize() {
		long totalPrize = 0;
		for (LottoRank lottoRank : rankResult.keySet()) {
			totalPrize += lottoRank.getTotal(rankResult.get(lottoRank));
		}
		return totalPrize;
	}
}
