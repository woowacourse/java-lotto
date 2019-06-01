package com.woowacourse.lotto.domain;

import java.util.*;

import static com.woowacourse.lotto.domain.Money.MINIMUM_MONEY_FOR_PURCHASE;

public class LottoResult {
	private final WinningLotto winningLotto;
	private final Lottos lottos;
	private static Map<LottoRank, Integer> rankResult = new TreeMap<>();

	static {
		rankResult.put(LottoRank.ZERO, 0);
		rankResult.put(LottoRank.FIFTH, 0);
		rankResult.put(LottoRank.FOURTH, 0);
		rankResult.put(LottoRank.THIRD, 0);
		rankResult.put(LottoRank.SECOND, 0);
		rankResult.put(LottoRank.FIRST, 0);
	}

	public LottoResult(final WinningLotto winningLotto, Lottos lottos) {
		this.winningLotto = winningLotto;
		this.lottos = lottos;
		matchLotto();
	}

	private Map<LottoRank, Integer> matchLotto() {
		for (Lotto lotto : lottos.getLottos()) {
			LottoRank lottoRank = LottoRank.valueOf(winningLotto.matchLotto(lotto), winningLotto.matchBonusBall(lotto));
			rankResult.put(lottoRank, rankResult.getOrDefault(lottoRank, 0) + 1);
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

	public Map<LottoRank, Integer> getLottoResult() {
		return rankResult;
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
