package lotto.domain;

import lotto.exception.InvalidMoneyException;
import lotto.validator.Validator;

public class Money {
	private static final int PRICE_ONE_LOTTO = 1_000;
	private static final int TO_PERCENT_VALUE = 100;

	private final int inputMoney;

	public Money(String inputMoney) {
		Validator.validateInteger(inputMoney);
		this.inputMoney = Integer.parseInt(inputMoney);
		validateOverThousand(this.inputMoney);
	}

	private void validateOverThousand(int inputMoney) {
		if (inputMoney < PRICE_ONE_LOTTO) {
			throw new InvalidMoneyException();
		}
	}

	public boolean canBuyLotto(int count) {
		return divideOneLottoPrice() >= count;
	}

	public int calculateLottoCount(int count) {
		return divideOneLottoPrice() - count;
	}

	private int divideOneLottoPrice() {
		return this.inputMoney / PRICE_ONE_LOTTO;
	}

	public int calculateIncomeRate(long income) {
		return (int)(income * TO_PERCENT_VALUE / inputMoney);
	}

}
