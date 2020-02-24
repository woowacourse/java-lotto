package domain;

import exception.LackOfMoneyException;

public class PurchaseMoney extends Money {

	private static final int LOTTO_PRICE = 1_000;

	private PurchaseMoney(double money) {
		super(money);
	}

	public static PurchaseMoney create(double money) {
		validate(money);
		return new PurchaseMoney(money);
	}

	private static void validate(double money) {
		if (money < LOTTO_PRICE) {
			throw new LackOfMoneyException("로또 비용이 부족합니다.");
		}
	}

}
