package lotto.domain;

import java.util.Map;

public class Result {
	private final Map<LottoRank, Integer> map;

	public Result(Map<LottoRank, Integer> map) {
		this.map = map;
	}

	public long calculateTotalPrize() {
		long totalPrize = 0;
		for (LottoRank lottoRank : map.keySet()) {
			totalPrize += lottoRank.getTotal(map.get(lottoRank));
		}
		return totalPrize;
	}
}
