package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lotto;

public abstract class LottoFactory {
	protected final int numberOfLotto;

	public LottoFactory(int numberOfLottos) {
		this.numberOfLotto = numberOfLottos;
	}

	abstract List<Lotto> generateLotto();
}
