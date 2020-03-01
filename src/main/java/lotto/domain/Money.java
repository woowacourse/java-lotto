package lotto.domain;

public class Money {
	public static final int LOTTO_PRICE = 1_000;
	private static final int MINIMUM_MONEY = 1_000;

	private int money;

	public Money(int money) {
		validate(money);
		this.money = money;
	}

	private void validate(int money) {
		validateLackOf(money);
		validateMoneyUnit(money);
	}

	private void validateLackOf(int money) {
		if (money < MINIMUM_MONEY) {
			throw new IllegalArgumentException("돈이 부족합니다.");
		}
	}

	private void validateMoneyUnit(int money) {
		if (money % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("천 원 단위로 입력해야 합니다.");
		}
	}

	public int getMoney() {
		return money;
	}
}
