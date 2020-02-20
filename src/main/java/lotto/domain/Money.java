package lotto.domain;

import lotto.validator.Validator;

public class Money {
	private static final int PRICE_ONE_LOTTO = 1000;
	private static final int TO_PERCENT_VALUE = 100;
	private static final String ERROR_MESSAGE_MIN_MONEY = "천원 이상의 금액만 가능합니다.";

	private final int inputMoney;

	public Money(String inputMoney) {
		Validator.validateInteger(inputMoney);
		this.inputMoney = Integer.parseInt(inputMoney);
		validateOverThousand();
	}

	private void validateOverThousand() {
		if (this.inputMoney < PRICE_ONE_LOTTO) {
			throw new IllegalArgumentException(ERROR_MESSAGE_MIN_MONEY);
		}
	}

	public int divideThousand() {
		return inputMoney / PRICE_ONE_LOTTO;
	}

	public int calculateIncomeRate(long income) {
		return (int)(income * TO_PERCENT_VALUE / inputMoney);
	}
}
