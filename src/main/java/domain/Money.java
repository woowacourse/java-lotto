package domain;

import static domain.exception.MoneyExceptionMessages.*;

public class Money {

	private static final int MINIMUM_MONEY = 1000;
	private static final int MAXIMUM_MONEY = 100000;

	private int money;

	public Money(final int money) {
		validateMoney(money);
		this.money = money;
	}

	private void validateMoney(final int money) {
		validateRange(money);
	}

	private void validateRange(final int money) {
		if (money < MINIMUM_MONEY || money > MAXIMUM_MONEY) {
			throw new IllegalArgumentException(INVALID_MONEY_RANGE_EXCEPTION.getMessage());
		}
	}

	public int divideBy(final int divisor) {
		return this.money / divisor;
	}
}
