package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;

public class LottoController {
	public static final int SUM_UNIT = 1;
	public static final int INIT_COUNT = 0;

	public List<Lotto> purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoGenerator.generate());
		}
		return lottos;
	}

	public Map<LottoRank, Integer> getLottoRankCount(List<Lotto> lottos, Lotto winningLotto,
		LottoNumber bonusLottoNumber) {
		Map<LottoRank, Integer> lottoRankCount = new TreeMap<>(Collections.reverseOrder());

		for (LottoRank lottoRank : LottoRank.values()) {
			lottoRankCount.put(lottoRank, INIT_COUNT);
		}

		for (Lotto lotto : lottos) {
			LottoRank lottoRank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.isContains(bonusLottoNumber));
			lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
		}
		return lottoRankCount;
	}
}
