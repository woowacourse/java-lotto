package com.woowacourse.lotto.domain.factory;

import com.woowacourse.lotto.domain.Lottos;

public abstract class LottoFactory {
	protected final int numberOfLotto;

	public LottoFactory(int numberOfLottos) {
		this.numberOfLotto = numberOfLottos;
	}

	abstract Lottos generateLotto();
}
