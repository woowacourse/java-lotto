package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lotto;

public abstract class LottoFactory {
	private final int countOfPurchasedLotto;

	public LottoFactory(int numberOfLottos) {
		this.countOfPurchasedLotto = numberOfLottos;
	}

	   public int getCountOfPurchasedLotto() {
		return countOfPurchasedLotto;
	}

	abstract List<Lotto> generateLotto();
}
