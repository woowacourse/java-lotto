package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

	private final EnumMap<LottoRank, Integer> winningResult;

	public WinningResult(final EnumMap<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public double getRateOfProfit(final Money money) {
		long profit = winningResult.entrySet()
			.stream()
			.mapToLong(result -> result.getKey().getAmount() * result.getValue())
			.sum();
		return (double)profit / (double)money.getMoney();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return Collections.unmodifiableMap(winningResult);
	}
}
