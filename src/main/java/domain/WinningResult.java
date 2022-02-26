package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private final Map<LottoRank, Integer> winningResult;

	public WinningResult(EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = new EnumMap<>(winningResult);
	}

	public double getRateOfProfit(Money money) {
		long profit = winningResult.entrySet()
			.stream()
			.mapToLong(this::calculateProfit)
			.sum();
		return (double)profit / (double)money.getMoney();
	}

	private long calculateProfit(Map.Entry<LottoRank, Integer> rankWithCount) {
		return getAmount(rankWithCount.getKey()) * getCount(rankWithCount);
	}

	private long getAmount(LottoRank rank) {
		return rank.getAmount();
	}

	private int getCount(Map.Entry<LottoRank, Integer> rankWithCount) {
		return rankWithCount.getValue();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return Collections.unmodifiableMap(winningResult);
	}
}
