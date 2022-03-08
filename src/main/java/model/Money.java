package model;

import utils.InputValidateUtils;

public class Money {
	private static final int UNIT = 1000;
	private static final String LOTTO_COUNT_UNIT_ERROR_MESSAGE = "[Error]: 금액은 천원 단위여야 합니다.";

	private final int money;

	public Money(int money) {
		InputValidateUtils.checkInputIsNegative(money);
		validateThousandUnitInputMoney(money);
		this.money = money;
	}

	private void validateThousandUnitInputMoney(int money) {
		if (money % UNIT != 0 || money == 0) {
			throw new IllegalArgumentException(LOTTO_COUNT_UNIT_ERROR_MESSAGE);
		}
	}

	public int makeMoneyToCount() {
		validateThousandUnitInputMoney(money);
		return money / UNIT;
	}

	public double getMoney() {
		return (double)money;
	}
}
