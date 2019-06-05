package com.woowacourse.lotto.domain;

import java.util.*;

public class LottoResult {
	private Map<LottoRank, Integer> rankResult = new TreeMap<>();

	public LottoResult(final WinningLotto winningLotto, final Lottos lottos) {
		initializeRankResultMap();
		matchLotto(winningLotto, lottos);
	}

	private void initializeRankResultMap() {
		for (LottoRank lottoRank : LottoRank.values()) {
			rankResult.put(lottoRank, 0);
		}
	}

	private void matchLotto(WinningLotto winningLotto, Lottos lottos) {
		for (Lotto lotto : lottos.getLottos()) {
			LottoRank lottoRank = LottoRank.valueOf(winningLotto.matchLotto(lotto), winningLotto.matchBonusBall(lotto));
			rankResult.put(lottoRank, rankResult.get(lottoRank) + 1);
		}
	}

	public double sum() {
		return rankResult.keySet().stream()
				.mapToDouble(rank -> (double) (rank.getPrice() * rankResult.get(rank)))
				.sum()
				;
	}

	public Map<LottoRank, Integer> getLottoResult() {
		return new TreeMap<>(rankResult);
	}
}
