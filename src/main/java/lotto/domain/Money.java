package lotto.domain;

import java.util.Objects;

public class Money {
	private static final String NOT_NUMBER = "금액은 숫자이어야 합니다.";
	private static final String NO_EMPTY = "금액은 공백이 될 수 없습니다.";
	private static final int PRICE_OF_LOTTO_TICKET = 1000;

	private final int money;

	public Money(final String money) {
		checkValidationOf(money);
		this.money = Integer.parseInt(money);
	}

	public static Money ticketPriceOf(int ticketCount) {
		return new Money(String.valueOf(ticketCount * PRICE_OF_LOTTO_TICKET));
	}

	public int getMoney() {
		return money;
	}

	private void checkValidationOf(final String lottoMoneyValue) {
		if (isNotNumber(lottoMoneyValue)) {
			throw new IllegalArgumentException(NOT_NUMBER);
		}
		if (isEmpty(lottoMoneyValue)) {
			throw new IllegalArgumentException(NO_EMPTY);
		}
	}

	private boolean isNotNumber(final String lottoMoneyValue) {
		return lottoMoneyValue.chars()
			.anyMatch(c -> !Character.isDigit(c));
	}

	private boolean isEmpty(final String lottoMoneyValue) {
		return lottoMoneyValue.equals("");
	}

	public Money minus(Money another) {
		return new Money(String.valueOf(this.money - another.money));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money)o;
		return money == money1.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
