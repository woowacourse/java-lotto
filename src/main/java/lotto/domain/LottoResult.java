package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {
	private final Map<LottoRank, Long> lottoResult;

	public LottoResult(Map<LottoRank, Long> lottoResult) {
		this.lottoResult = Collections.unmodifiableMap(lottoResult);
	}

	Prize calculateTotalPrize() {
		return lottoResult.keySet().stream()
			.map(rank -> rank.calculateTotalPrize(lottoResult.get(rank)))
			.reduce(Prize.of(0), Prize::plus);
	}

	public long getProfitRate(Money money) {
		Prize totalPrize = calculateTotalPrize();
		return totalPrize.findProfits(money);
	}

	public Map<LottoRank, Long> getLottoResult() {
		return lottoResult;
	}
}
