package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;

public class LottoFactoryController {
	private int countOfAllLotto;
	private int countOfManualLotto;

	public LottoFactoryController(Money money, int countOfManualLotto) {
		this.countOfAllLotto = money.getCountOfLotto();
		this.countOfManualLotto = countOfManualLotto;

		if (countOfAllLotto < countOfManualLotto) {
			throw new InvalidCountOfManualLottoException("구입 금액보다 로또의 개수가 더 큽니다. 다시 입력해주세요.");
		}
	}

	public Lottos generateLotto(List<String> numbers) {
		return new ManualLottoFactory(countOfManualLotto, numbers).generateLotto()
				.addLottos(new AutomaticLottoFactory(countOfAllLotto - countOfManualLotto).generateLotto());
	}
}
