package lotto.domain;

import lotto.exception.InvalidLottoCountException;
import lotto.validator.Validator;

public class ManualLottoCount {
	private static final int MIN_COUNT = 1;

	private int count;
	private final Money money;

	public ManualLottoCount(String count, Money money) {
		Validator.validateInteger(count);
		this.count = Integer.parseInt(count);
		this.money = money;
		validProperCount(this.count);
	}

	private void validProperCount(int lottoCount) {
		if (isPositiveAndCanBuy(lottoCount) == false) {
			throw new InvalidLottoCountException();
		}
	}

	private boolean isPositiveAndCanBuy(int lottoCount) {
		return lottoCount >= MIN_COUNT && this.money.canBuyLotto(lottoCount);
	}

	public boolean hasNext() {
		return count-- >= MIN_COUNT;
	}
}
