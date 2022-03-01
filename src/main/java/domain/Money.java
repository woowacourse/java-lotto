package domain;

import java.util.Objects;

public class Money {

	public static final int LOTTO_PRICE = 1000;
	private static final int REMAINDER_ZERO = 0;
	private static final String NOT_MONEY_UNIT_EQUAL_LOTTO_PRICE_UNIT_MESSAGE = "구입 금액은 1000원 단위여야 합니다";
	private static final String NOT_MONEY_DIGIT_MESSAGE = "구입 금액은 숫자여야 합니다";
	private final int money;

	public Money(final int money) {
		checkUnit(money);
		this.money = money;
	}

	public static Money from(final String userInput) {
		checkNotDigit(userInput);
		return new Money(Integer.parseInt(userInput));
	}

	private void checkUnit(final int money) {
		if (money % LOTTO_PRICE != REMAINDER_ZERO) {
			throw new IllegalArgumentException(NOT_MONEY_UNIT_EQUAL_LOTTO_PRICE_UNIT_MESSAGE);
		}
	}

	private static void checkNotDigit(final String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException(NOT_MONEY_DIGIT_MESSAGE);
		}
	}

	public int findPurchaseLottoCount() {
		return money / LOTTO_PRICE;
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

	public int getMoney() {
		return money;
	}
}
