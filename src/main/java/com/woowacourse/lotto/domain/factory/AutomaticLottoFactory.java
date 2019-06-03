package com.woowacourse.lotto.domain.factory;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;

public class AutomaticLottoFactory extends LottoFactory {
	public AutomaticLottoFactory(int countOfPurchasedLotto) {
		super(countOfPurchasedLotto);
	}

	@Override
	public List<Lotto> generateLotto() {
		List<Lotto> lotto = new ArrayList<>();

		for (int i = 0; i < super.getCountOfPurchasedLotto(); i++) {
			lotto.add(new Lotto(LottoNumber.generateLotto()));
		}

		return lotto;
	}
}
