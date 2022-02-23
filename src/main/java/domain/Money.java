package domain;

import java.util.Objects;

public class Money {

	private final int money;

	private Money(int money) {
		checkUnit(money);
		this.money = money;
	}

	private void checkUnit(int money) {
		if (money % 1000 != 0) {
			throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다");
		}
	}

	public static Money from(String userInput) {
		checkNotDigit(userInput);
		return new Money(Integer.parseInt(userInput));
	}

	private static void checkNotDigit(String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("구입 금액은 숫자여야 합니다");
		}
	}

	public boolean isPossibleToPurchase(int money) {
		return this.money >= money;
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
