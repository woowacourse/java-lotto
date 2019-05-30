package com.woowacourse.lotto.domain;

import java.util.*;

import static com.woowacourse.lotto.domain.Money.MINIMUM_MONEY_FOR_PURCHASE;

public class LottoResult {
	private final WinningLotto winningLotto;
	private final Lottos lottos;
	private Map<LottoRank, Integer> rankResult;

	public LottoResult(WinningLotto winningLotto, Lottos lottos) {
		this.winningLotto = winningLotto;
		this.lottos = lottos;
	}

	public Map<LottoRank, Integer> matchLotto() {
		rankResult = new TreeMap<>();

		for (LottoRank lottoRank : LottoRank.values()) {
			rankResult.put(lottoRank, 0);
		}

		for (Lotto lotto : lottos.getLottos()) {
			int count = winningLotto.matchLotto(lotto);
			LottoRank lottoRank = LottoRank.valueOf(count, winningLotto.matchBonusBall(lotto));
			rankResult.put(lottoRank, rankResult.get(lottoRank) + 1);
		}

		return rankResult;
	}

	private int getAllPrice() {
		int sum = 0;

		for (LottoRank rank : rankResult.keySet()) {
			sum += (rank.getPrice() * rankResult.get(rank));
		}

		return sum;
	}

	public double getEarningsRate() {
		return ((double) getAllPrice() / (double) (lottos.getSize() * MINIMUM_MONEY_FOR_PURCHASE)) * 100;
	}
}
