package lotto.domain;

import java.util.Optional;

import lotto.exceptions.NotAllowedMoneyAmountException;

public class Money {
	private static final int MIN_MONEY = 0;
	private static final String NOT_ALLOWED_MONEY_AMOUNT_MESSAGE = "구입금액은 자연수로 입력해주세요.";

	private int money;

	public Money(int money) {
		try {
			this.money = Optional.of(money)
				.filter(this::isNaturalNumber)
				.orElseThrow(NumberFormatException::new);
		} catch (NumberFormatException e) {
			throw new NotAllowedMoneyAmountException(NOT_ALLOWED_MONEY_AMOUNT_MESSAGE);
		}
	}

	private boolean isNaturalNumber(int money) {
		return money > MIN_MONEY;
	}

	public int getQuotient(Money money) {
		return this.money / money.money;
	}

	public Money sum(Money money) {
		return new Money(this.money + money.money);
	}

	public Money multiple(int operand) {
		return new Money(this.money * operand);
	}

	@Override
	public String toString() {
		return String.valueOf(money);
	}
}
