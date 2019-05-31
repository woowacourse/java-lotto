package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.ExceptionOutput;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;

public class LottosFactory {
	private int countOfAllLotto;
	private int countOfManualLotto;

	public LottosFactory(Money money, int countOfManualLotto) {
		this.countOfAllLotto = money.getCountOfLotto();
		this.countOfManualLotto = countOfManualLotto;

		if (countOfAllLotto < countOfManualLotto) {
			throw new InvalidCountOfManualLottoException(ExceptionOutput.VIOLATE_PURCHASED_LOTTO.getExceptionMessage());
		}
	}

	public Lottos generateLotto(List<String> numbers) {
		return new ManualLottoFactory(countOfManualLotto, numbers).generateLotto()
				.addLottos(new AutomaticLottoFactory(countOfAllLotto - countOfManualLotto).generateLotto());
	}
}
