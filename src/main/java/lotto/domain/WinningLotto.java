package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WinningLotto {
	private static final int SUM_UNIT = 1;
	private static final int INIT_COUNT = 0;

	private Lotto winningLotto;
	private LottoNumber bonusLottoNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusLottoNumber) {
		this.winningLotto = winningLotto;
		this.bonusLottoNumber = bonusLottoNumber;
	}

	public Map<LottoRank, Integer> getWinningLottoCount(List<Lotto> lottos) {
		Map<LottoRank, Integer> lottoRankCount = new TreeMap<>(Collections.reverseOrder());

		Arrays.stream(LottoRank.values())
			.forEach(lottoRank -> lottoRankCount.put(lottoRank, INIT_COUNT));

		for (Lotto lotto : lottos) {
			LottoRank lottoRank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.isContains(bonusLottoNumber));
			lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
		}
		return lottoRankCount;
	}

	public int getWinningRatio(Map<LottoRank, Integer> lottoRankCount, LottoMoney inputLottoMoney) {
		LottoMoney initLottoMoney = LottoMoney.MISS_PRIZE;
		for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
			initLottoMoney = initLottoMoney.add(lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return initLottoMoney.getWinningRatio(inputLottoMoney);
	}
}
