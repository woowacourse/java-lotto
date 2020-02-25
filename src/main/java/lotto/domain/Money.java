package lotto.domain;

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
	private static final int LOTTO_PRICE = 1_000;

	private final int money;

	public Money(final Integer inputMoney) {
		Objects.requireNonNull(inputMoney);

		if (inputMoney < 0) {
			throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
		}
		this.money = inputMoney;
	}

	public int getMoney() {
		return this.money;
	}
}
