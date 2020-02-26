package lotto.domain;

import java.util.Iterator;

import lotto.exception.InvalidMoneyException;
import lotto.validator.Validator;

public class Money implements Iterator<Integer> {
	private static final int PRICE_ONE_LOTTO = 1_000;
	private static final int TO_PERCENT_VALUE = 100;
	private static final int MIN_MONEY = 0;

	private int inputMoney;

	public Money(String inputMoney) {
		Validator.validateInteger(inputMoney);
		int money = Integer.parseInt(inputMoney);
		validateOverThousand(money);
		this.inputMoney = money;
	}

	private void validateOverThousand(int inputMoney) {
		if (inputMoney < PRICE_ONE_LOTTO) {
			throw new InvalidMoneyException();
		}
	}

	public boolean isBuyLotto(int lottoCount) {
		return this.inputMoney >= (PRICE_ONE_LOTTO * lottoCount);
	}

	public int divideThousand() {
		return inputMoney / PRICE_ONE_LOTTO;
	}

	public int calculateIncomeRate(long income) {
		return (int)(income * TO_PERCENT_VALUE / inputMoney);
	}

	@Override
	public boolean hasNext() {
		return inputMoney - PRICE_ONE_LOTTO >= MIN_MONEY;
	}

	@Override
	public Integer next() {
		return inputMoney -= PRICE_ONE_LOTTO;
	}
}
