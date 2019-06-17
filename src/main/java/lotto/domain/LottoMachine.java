package lotto.domain;

import lotto.domain.exception.LackOfMoneyException;

public class LottoMachine {
	public static final int LOTTO_MONEY = 1000;

	private int remainMoney;

	public LottoMachine() {
		remainMoney = 0;
	}

	public void charge(final int money) {
		if (money < LOTTO_MONEY) {
			throw new LackOfMoneyException("1000원 이상 구매하세요");
		}
		remainMoney = money;
	}

	public void buy() {
		this.remainMoney -= LOTTO_MONEY;
	}

	public boolean isRemainMoney() {
		return (this.remainMoney >= LOTTO_MONEY);
	}
}
