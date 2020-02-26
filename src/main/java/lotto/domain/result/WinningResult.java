package lotto.domain.result;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import lotto.domain.purchase.LottoMoney;

public class WinningResult {
	private final Map<LottoRank, Long> winningResult = new TreeMap<>(Collections.reverseOrder());

	public WinningResult(Map<LottoRank, Long> winningResult) {
		validate(winningResult);
		this.winningResult.putAll(putIfAbsentLottoRankAndRemoveMissLottoRank(winningResult));
	}

	private void validate(Map<LottoRank, Long> winningResult) {
		if (Objects.isNull(winningResult) || winningResult.isEmpty()) {
			throw new InvalidWinningResultException(InvalidWinningResultException.NULL_OR_EMPTY);
		}
	}

	private Map<LottoRank, Long> putIfAbsentLottoRankAndRemoveMissLottoRank(Map<LottoRank, Long> winningResult) {
		for (LottoRank lottoRank : LottoRank.values()) {
			winningResult.putIfAbsent(lottoRank, 0L);
		}
		winningResult.remove(LottoRank.MISS);

		return winningResult;
	}

	public long calculateWinningRate(LottoMoney lottoMoney) {
		LottoMoney totalLottoMoney = calculateTotalWinningLottoMoney();
		return totalLottoMoney.measureWinningRate(lottoMoney);
	}

	private LottoMoney calculateTotalWinningLottoMoney() {
		return winningResult.entrySet().stream()
			.map(entry -> {
				LottoRank lottoRank = entry.getKey();
				Long lottoRankCount = entry.getValue();
				return lottoRank.calculateWinningLottoMoneyBy(lottoRankCount);
			})
			.reduce(LottoMoney.ZERO, LottoMoney::addBy);
	}

	public Map<LottoRank, Long> getWinningResult() {
		return winningResult;
	}
}
