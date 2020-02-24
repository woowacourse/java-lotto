package lotto.domain.result;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

public class LottoRankRecord {
	private static final int SUM_UNIT = 1;
	private static final int INIT_COUNT = 0;

	private Map<LottoRank, Integer> lottoRankCount;

	public LottoRankRecord(Lottos lottos, WinningLotto winningLotto) {
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

	public Map<LottoRank, Integer> getLottoRankCount() {
		return lottoRankCount;
	}
}
