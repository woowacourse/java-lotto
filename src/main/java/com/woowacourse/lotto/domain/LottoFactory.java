package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
	private final int numberOfLotto;

	public LottoFactory(Money money) {
		numberOfLotto = money.getCountOfLotto();
		generateLotto();
	}

	public Lottos generateLotto() {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(new Lotto(LottoNumber.getLottos()));
		}

		return new Lottos(lottos);
	}
}
