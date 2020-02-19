package lotto.domain;

import java.util.Optional;

import lotto.exceptions.NotAllowedMoneyAmountException;

public class Money {
	private static final int MIN_MONEY = 0;
	private int money;

	public Money(String money) {
		try {
			this.money = Optional.of(Integer.parseInt(money))
				.filter(this::isNaturalNumber)
				.orElseThrow(NumberFormatException::new);
		} catch (NumberFormatException e) {
			throw new NotAllowedMoneyAmountException("구입금액은 자연수로 입력해주세요.");
		}
	}

	public int getMoney() {
		return money;
	}

	private boolean isNaturalNumber(int money) {
		return money > MIN_MONEY;
	}
}
