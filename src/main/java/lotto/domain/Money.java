package lotto.domain;

import java.util.Iterator;

import lotto.exception.InvalidMoneyException;
import lotto.validator.Validator;

public class Money implements Iterator<Integer> {
	private static final int PRICE_ONE_LOTTO = 1_000;
	private static final int TO_PERCENT_VALUE = 100;
	private static final int MIN_COUNT = 1;

	private final int inputMoney;
	private int ableBuyLottoCount;

	public Money(String inputMoney) {
		Validator.validateInteger(inputMoney);
		int money = Integer.parseInt(inputMoney);
		validateOverThousand(money);
		this.inputMoney = money;
		this.ableBuyLottoCount = this.inputMoney / PRICE_ONE_LOTTO;
	}

	private void validateOverThousand(int inputMoney) {
		if (inputMoney < PRICE_ONE_LOTTO) {
			throw new InvalidMoneyException();
		}
	}

	public boolean isBuyLotto(int lottoCount) {
		return this.ableBuyLottoCount >= lottoCount;
	}

	public int calculateIncomeRate(long income) {
		return (int)(income * TO_PERCENT_VALUE / inputMoney);
	}

	@Override
	public boolean hasNext() {
		return ableBuyLottoCount >= MIN_COUNT;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new IllegalArgumentException();
		}
		return --ableBuyLottoCount;
	}
}
