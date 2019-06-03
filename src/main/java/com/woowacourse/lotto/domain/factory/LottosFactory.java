package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;

public class LottosFactory {
	public static final String VIOLATE_PURCHASED_LOTTO = "구입 금액보다 로또의 개수가 더 큽니다. 다시 입력해주세요.";
	private int countOfAllLotto;
	private int countOfManualLotto;

	public LottosFactory(Money money, int countOfManualLotto) {
		if (money.getCountOfLotto() < countOfManualLotto) {
			throw new InvalidCountOfManualLottoException(VIOLATE_PURCHASED_LOTTO);
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
