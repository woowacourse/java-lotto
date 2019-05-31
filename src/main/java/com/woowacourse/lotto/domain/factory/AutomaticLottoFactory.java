package com.woowacourse.lotto.domain.factory;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.Lottos;

public class AutomaticLottoFactory extends LottoFactory {
	public AutomaticLottoFactory(int countOfPurchasedLotto) {
		super(countOfPurchasedLotto);
	}

	@Override
	public Lottos generateLotto() {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(new Lotto(LottoNumber.getLotto()));
		}

		return new Lottos(lottos);
	}
}
