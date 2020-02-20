package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;

public class LottoController {
	public static final int INIT_COUNT = 1;

	public List<Lotto> purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoGenerator.generate());
		}
		return lottos;
	}

	public Map<LottoRank, Integer> getLottoRankCount(List<Lotto> lottos, Lotto winningLotto,
		LottoNumber bonusLottoNumber) {
		Map<LottoRank, Integer> lottoRankCount = new TreeMap<>();

		for (Lotto lotto : lottos) {
			LottoRank lottoRank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.isContains(bonusLottoNumber));
			lottoRankCount.merge(lottoRank, INIT_COUNT, Integer::sum);
		}
		return lottoRankCount;
	}
}
