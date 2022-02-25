package domain;

import java.util.Objects;

public class Money {

	private static final String NOT_DIGIT_EXCEPTION_MESSAGE = "구입 금액은 숫자여야 합니다";
	private static final String UNAVAILABLE_MONEY_EXCEPTION_MESSAGE = "구입 금액은 1000원 단위여야 합니다";
	private static final int LOTTO_PRICE = 1000;

	private final int money;

	private Money(int money) {
		checkUnitOfMoney(money);
		this.money = money;
	}

	public static Money from(String userInput) {
		checkNotDigit(userInput);
		return new Money(Integer.parseInt(userInput));
	}

	private void checkUnitOfMoney(int money) {
		if (!isValidMoney(money)) {
			throw new IllegalArgumentException(UNAVAILABLE_MONEY_EXCEPTION_MESSAGE);
		}
	}

	private boolean isValidMoney(int money) {
		return money % LOTTO_PRICE == 0;
	}

	private static void checkNotDigit(String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException(NOT_DIGIT_EXCEPTION_MESSAGE);
		}
	}

	public boolean isPossibleToPurchase(int purchasePrice) {
		return money >= purchasePrice;
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
