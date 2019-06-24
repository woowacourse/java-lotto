package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;

public class LottosFactory {
	private static final String VIOLATE_PURCHASED_LOTTO = "구매할 로또 수를 잘못 입력하셨습니다.";
	private int countOfAllLotto;
	private int countOfManualLotto;

	public LottosFactory(LottoMoney lottoMoney, int countOfManualLotto) {
		if (countOfManualLotto < 0 || lottoMoney.getCountOfLotto() < countOfManualLotto) {
			throw new InvalidCountOfManualLottoException(VIOLATE_PURCHASED_LOTTO);
		}
		this.countOfAllLotto = lottoMoney.getCountOfLotto();
		this.countOfManualLotto = countOfManualLotto;
	}

	public Lottos generateLotto(List<String> numbers) {
		List<Lotto> lotto = new AutomaticLottoFactory(countOfAllLotto - countOfManualLotto).generateLotto();
		if (numbers != null) {
			lotto.addAll(0, new ManualLottoFactory(numbers).generateLotto());
		}
		return new Lottos(lotto);
	}
}
