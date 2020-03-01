package lotto.domain.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.Money;

public class WinningResult {
	private final Map<LottoRank, Long> winningResult;

	public WinningResult(Map<LottoRank, Long> result) {
		this.winningResult = Collections.unmodifiableMap(new HashMap<>(result));
	}

	public long calculateProfitRate() {
		Money totalPrize = calculateTotalPrize();
		Money totalMoney = calculateTotalMoney();
		return totalPrize.calculatePercentage(totalMoney);
	}

	private Money calculateTotalPrize() {
		return winningResult.keySet().stream()
			.map(rank -> rank.calculateTotalMoney(winningResult.get(rank)))
			.reduce(Money.valueOf(0), Money::plus);
	}

	private Money calculateTotalMoney() {
		return winningResult.keySet().stream()
			.map(winningResult::get)
			.map(count -> Money.valueOf(LottoTicket.PRICE * count))
			.reduce(Money.valueOf(0), Money::plus);
	}

	public long findWinningCount(LottoRank rank) {
		return winningResult.get(rank);
	}

	Map<LottoRank, Long> getWinningResult() {
		return winningResult;
	}
}
