package lotto.domain.result;

import java.util.Map;
import java.util.Objects;

import lotto.domain.ticket.Money;

public class TotalResult {
	private final WinningResult winningResult;
	private final Money money;

	public TotalResult(WinningResult winningResult, Money money) {
		this.winningResult = Objects.requireNonNull(winningResult);
		this.money = Objects.requireNonNull(money);
	}

	public long getProfitRate() {
		Money totalMoney = winningResult.calculateTotalMoney();
		return totalMoney.calculateProfitRate(money);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult.getWinningResult();
	}
}
