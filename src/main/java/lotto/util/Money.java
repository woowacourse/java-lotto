package lotto.util;

import java.util.Objects;

/**
 * Money 클래스
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Money {
	private final long money;

	public Money(final long inputMoney) {
		if (inputMoney < 0) {
			throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
		}
		this.money = inputMoney;
	}

	public Money add(Money money) {
		return new Money(this.money + money.money);
	}

	public Money subtract(Money money) {
		return new Money(this.money - money.money);
	}

	public Count divide(Money money) {
		return new Count(this.money / money.money);
	}

	public boolean isLessThen(Money money) {
		return this.money - money.money < 0;
	}

	public long getMoney() {
		return this.money;
	}
}
