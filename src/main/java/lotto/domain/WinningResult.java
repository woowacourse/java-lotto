package lotto.domain;

import java.util.Map;
import java.util.stream.Stream;

import lotto.domain.lotto.LottoMoney;

public class WinningResult {
	private static final int PERCENT = 100;
	private final Map<LottoRank, Integer> winningResult;

	WinningResult(Map<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public int getWinningRatio(LottoMoney lottoMoney) {
		return (int)(getTotalPrize() * PERCENT / lottoMoney.getMoney());
	}

	private long getTotalPrize() {
		return winningResult.entrySet()
			.stream()
			.mapToLong(e -> (e.getKey().getWinningMoney() * e.getValue()))
			.sum();
	}

	public Stream<Map.Entry<LottoRank, Integer>> exceptMiss() {
		return winningResult.entrySet().stream()
			.filter(entry -> !entry.getKey().isLottoRankOf(LottoRank.MISS));
	}
}
