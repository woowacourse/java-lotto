package lotto.domain;

import java.util.Optional;

import lotto.exceptions.NotAllowedMoneyAmountException;

public class Money {
	private static final long MIN_MONEY = 0;
	private static final String NOT_ALLOWED_MONEY_AMOUNT_MESSAGE = "구입금액은 자연수로 입력해주세요.";

	private long money;

	public Money(long money) {
		try {
			this.money = Optional.of(money)
				.filter(this::isNaturalNumber)
				.orElseThrow(NumberFormatException::new);
		} catch (NumberFormatException e) {
			throw new NotAllowedMoneyAmountException(NOT_ALLOWED_MONEY_AMOUNT_MESSAGE);
		}
	}

	private boolean isNaturalNumber(long money) {
		return money > MIN_MONEY;
	}

	public long getQuotient(Money other) {
		return this.money / other.money;
	}

	public Money sum(Money other) {
		return new Money(this.money + other.money);
	}

	public Money multiple(int operand) {
		return new Money(this.money * operand);
	}

	@Override
	public String toString() {
		return String.valueOf(money);
	}
}
