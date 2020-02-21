package lotto.domain;

import java.util.Objects;

public class Money {
	private static final int MONEY_ZERO = 0;
	private static final int LOTTO_PRICE = 1000;

	private static final String MONEY_EXCEPTION_MESSAGE = String.format("금액을 %d원 단위로 입력해주세요.", LOTTO_PRICE);
	public static final int PROFIT_PERCENTAGE = 100;

	private final long money;

	private Money(long money) {
		validatePositive(money);
		validateDivideByThousand(money);
		this.money = money;
	}

	public static Money of(long money) {
		return new Money(money);
	}

	private void validatePositive(long money) {
		if (money < MONEY_ZERO) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	private void validateDivideByThousand(long money) {
		if (money % LOTTO_PRICE != MONEY_ZERO) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	public LottoCount findLottoTicketCount() {
		return LottoCount.of((int)money / LOTTO_PRICE);
	}

	public long getMoney() {
		return money;
	}

	public Money multiply(long multiplier) {
		return new Money(money * multiplier);
	}

	public Money plus(Money otherMoney) {
		return new Money(money + otherMoney.money);
	}

	public long findProfits(Money lottoPrice) {
		return money * PROFIT_PERCENTAGE / lottoPrice.money;
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

	@Override
	public String toString() {
		return String.valueOf(money);
	}
}
