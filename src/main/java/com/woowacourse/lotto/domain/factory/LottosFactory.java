package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.ExceptionOutput;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;

public class LottosFactory {
	private int countOfAllLotto;
	private int countOfManualLotto;

	public LottosFactory(Money money, int countOfManualLotto) {
		if (countOfAllLotto < countOfManualLotto) {
			throw new InvalidCountOfManualLottoException(ExceptionOutput.VIOLATE_PURCHASED_LOTTO.getExceptionMessage());
		}
		this.countOfAllLotto = money.getCountOfLotto();
		this.countOfManualLotto = countOfManualLotto;
	}

	public Lottos generateLotto(List<String> numbers) {
		List<Lotto> lotto = new ManualLottoFactory(countOfAllLotto, numbers).generateLotto();
		lotto.addAll(new AutomaticLottoFactory(countOfAllLotto - countOfManualLotto).generateLotto());
		return new Lottos(lotto);
	}
}
