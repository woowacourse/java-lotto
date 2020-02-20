package lotto.domain;

import java.util.Map;

public class TotalResult {
	private final LottoResult lottoResult;
	private final LottoCount count;

	public TotalResult(LottoResult lottoResult, LottoCount count) {
		this.lottoResult = lottoResult;
		this.count = count;
	}

	public long getProfitRate() {
		return lottoResult.calculateTotalPrize() * 100 / Money.getBuyMoney(count);
	}

	public Map<LottoRank, Integer> getLottoResult() {
		return lottoResult.getRankResult();
	}
}
