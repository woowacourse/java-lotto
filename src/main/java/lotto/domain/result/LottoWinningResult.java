package lotto.domain.result;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottomoney.LottoMoney;

public class LottoWinningResult {
	private static final int SUM_UNIT = 1;
	private static final int INIT_COUNT = 0;

	private Map<LottoRank, Integer> lottoRankCount;

	public LottoWinningResult(Lottos lottos, WinningLotto winningLotto) {
		lottoRankCount = new TreeMap<>(Collections.reverseOrder());
		calculate(lottos, winningLotto);
	}

	private void calculate(Lottos lottos, WinningLotto winningLotto) {
		for (LottoRank lottoRank : LottoRank.values()) {
			lottoRankCount.put(lottoRank, INIT_COUNT);
		}

		for (Lotto lotto : lottos) {
			LottoRank lottoRank = LottoRank.of(lotto.calculateMatchCount(winningLotto.getLotto()), lotto.contains(winningLotto.getBonusNumber()));
			lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
		}
	}

	public int calculateWinningRatio(Map<LottoRank, Integer> lottoRankCount, LottoMoney inputLottoMoney) {
		LottoMoney totalWinningMoney = LottoRank.MISS.getWinningMoney();
		for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
			totalWinningMoney = totalWinningMoney.add(
				lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return totalWinningMoney.getWinningRate(inputLottoMoney);
	}

	public Map<LottoRank, Integer> getLottoRankCount() {
		return lottoRankCount;
	}
}
