package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {
	private final Map<LottoRank, Long> lottoResult;

	public LottoResult(Map<LottoRank, Long> lottoResult) {
		this.lottoResult = Collections.unmodifiableMap(lottoResult);
	}

	Money calculateTotalMoney() {
		return lottoResult.keySet().stream()
			.map(rank -> rank.calculateTotalMoney(lottoResult.get(rank)))
			.reduce(Money.of(0), Money::plus);
	}

	public long getProfitRate(Money money) {
		Money totalMoney = calculateTotalMoney();
		return totalMoney.findProfits(money);
	}

	public Map<LottoRank, Long> getLottoResult() {
		return lottoResult;
	}
}
