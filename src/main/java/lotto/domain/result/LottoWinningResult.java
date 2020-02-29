package lotto.domain.result;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.lotto.Lotto;
import lotto.domain.money.Money;

public class LottoWinningResult {
	private static final int SUM_UNIT = 1;
	private static final int INIT_COUNT = 0;

	private Map<LottoRank, Integer> lottoRankCount;

	public LottoWinningResult(List<Lotto> lottoTicket, WinningLotto winningLotto) {
		lottoRankCount = new TreeMap<>(Collections.reverseOrder());

		for (LottoRank lottoRank : LottoRank.values()) {
			lottoRankCount.put(lottoRank, INIT_COUNT);
		}

		calculate(lottoTicket, winningLotto);
	}

	private void calculate(List<Lotto> lottoTicket, WinningLotto winningLotto) {
		for (Lotto lotto : lottoTicket) {
			LottoRank lottoRank = LottoRank.of(
				lotto.calculateMatchCount(winningLotto.getLotto()),
				lotto.contains(winningLotto.getBonusNumber()));
			lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
		}
	}

	public int calculateWinningRatio(Money inputMoney) {
		Money totalWinningMoney = LottoRank.MISS.getWinningMoney();
		for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
			totalWinningMoney = totalWinningMoney.add(
				lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return totalWinningMoney.getWinningRate(inputMoney);
	}

	public Map<LottoRank, Integer> getLottoRankCount() {
		return lottoRankCount;
	}
}
