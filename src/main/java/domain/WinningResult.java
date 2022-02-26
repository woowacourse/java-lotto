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

	private long calculateProfit(Map.Entry<LottoRank, Integer> rank) {
		return rank.getKey().getAmount() * rank.getValue();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return Collections.unmodifiableMap(winningResult);
	}
}
