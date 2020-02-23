package domain;

import exception.LackOfMoneyException;

public class PurchaseMoney extends Money {

	private static final int LOTTO_PRICE = 1_000;

	public PurchaseMoney(int money) {
		super(money);
		validate(money);
	}

	private void validate(int money) {
		if (money < LOTTO_PRICE) {
			throw new LackOfMoneyException("로또 비용이 부족합니다.");
		}
	}
}
