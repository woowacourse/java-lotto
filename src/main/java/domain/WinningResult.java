package domain;

import java.util.Map;

public class WinningResult {

	private final Map<LottoRank, Integer> winningResult;

	public WinningResult(final Map<LottoRank, Integer> winningResult) {
		this.winningResult = Map.copyOf(winningResult);
	}

	public double getRateOfProfit(final Money money) {
		if (money.canPurchase()) {
			return 0;
		}
		long profit = winningResult.entrySet()
			.stream()
			.mapToLong(result -> result.getKey().getAmount() * result.getValue())
			.sum();
		return (double)profit / (double)money.getMoney();
	}

	public Map<LottoRank, Integer> getWinningResult() {
		return winningResult;
	}
}
