package lotto.domain;

import java.util.Map;
import java.util.stream.Stream;

import lotto.domain.lotto.LottoMoney;

public class WinningResult {
	private final Map<LottoRank, Integer> winningResult;

	public WinningResult(Map<LottoRank, Integer> winningResult) {
		this.winningResult = winningResult;
	}

	public int getWinningRatio(LottoMoney inputLottoMoney) {
		LottoMoney initLottoMoney = LottoRank.MISS.getWinningMoney();
		for (Map.Entry<LottoRank, Integer> lottoEntry : winningResult.entrySet()) {
			initLottoMoney = initLottoMoney.add(lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return initLottoMoney.getWinningRatio(inputLottoMoney);
	}

	public Stream<Map.Entry<LottoRank, Integer>> exceptMiss() {
		return winningResult.entrySet().stream()
			.filter(entry -> !entry.getKey().isLottoRankOf(LottoRank.MISS));
	}
}
