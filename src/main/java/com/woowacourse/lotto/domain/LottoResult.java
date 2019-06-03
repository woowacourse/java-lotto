package com.woowacourse.lotto.domain;

import java.util.*;

import static com.woowacourse.lotto.domain.Money.MINIMUM_MONEY_FOR_PURCHASE;

public class LottoResult {
	private static final int PERCENT = 100;
	private final WinningLotto winningLotto;
	private final Lottos lottos;
	private Map<LottoRank, Integer> rankResult = new TreeMap<>();

	public LottoResult(final WinningLotto winningLotto, Lottos lottos) {
		this.winningLotto = winningLotto;
		this.lottos = lottos;
		initializeRankResultMap();
		matchLotto();
	}

	private void initializeRankResultMap() {
		for (LottoRank lottoRank : LottoRank.values()) {
			rankResult.put(lottoRank, 0);
		}
	}

	private void matchLotto() {
		for (Lotto lotto : lottos.getLottos()) {
			LottoRank lottoRank = LottoRank.valueOf(winningLotto.matchLotto(lotto), winningLotto.matchBonusBall(lotto));
			rankResult.put(lottoRank, rankResult.get(lottoRank) + 1);
		}
	}

	private double sum() {
		return rankResult.keySet().stream().mapToDouble(rank -> (double) (rank.getPrice() * rankResult.get(rank))).sum();
	}

	public long getEarningsRate() {
		return (long) ((sum() / (lottos.size() * MINIMUM_MONEY_FOR_PURCHASE)) * PERCENT);
	}

	public Map<LottoRank, Integer> getLottoResult() {
		return new TreeMap<>(rankResult);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LottoResult)) {
			return false;
		}
		final LottoResult that = (LottoResult) o;
		return Objects.equals(winningLotto, that.winningLotto) &&
				Objects.equals(lottos, that.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLotto, lottos);
	}
}
