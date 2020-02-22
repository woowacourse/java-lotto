package lotto.domain;

import java.util.Map;

public class TotalResult {
	private final WinningResult winningResult;
	private final Money money;

	public TotalResult(WinningResult winningResult, Money money) {
		this.winningResult = winningResult;
		this.money = money;
	}

	public long getProfitRate() {
		Money totalMoney = winningResult.calculateTotalMoney();
		return totalMoney.calculateProfitRate(money);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult.getWinningResult();
	}
}
