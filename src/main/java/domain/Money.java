package domain;

public class Money {

	private static final int MINIMUM_MONEY = 1000;
	private static final int MAXIMUM_MONEY = 100000;

	private int money;

	public Money(int money) {
		validateMoney(money);
		this.money = money;
	}

	private void validateMoney(int money) {
		validateRange(money);
	}

	private void validateRange(int money) {
		if (money < MINIMUM_MONEY || money > MAXIMUM_MONEY) {
			throw new IllegalArgumentException("구입 금액의 범위는 1000원~100000원 입니다.");
		}
	}
}
