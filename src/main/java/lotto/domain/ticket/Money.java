package lotto.domain.ticket;

import java.util.Objects;

public final class Money {
	private static final int PROFIT_PERCENTAGE = 100;
	private static final int MINIMUM_MONEY = 0;
	private static final int DEFAULT_PERCENTAGE = 0;
	private static final String NOT_GREATER_THAN_MINIMUM_EXCEPTION_MESSAGE = "%d 보다 작은 값은 받을 수 없습니다.";

	private final long money;

	private Money(long money) {
		validateGreaterThanMinimum(money);
		this.money = money;
	}

	private void validateGreaterThanMinimum(long money) {
		if (money < MINIMUM_MONEY) {
			throw new IllegalArgumentException(
				String.format(NOT_GREATER_THAN_MINIMUM_EXCEPTION_MESSAGE, MINIMUM_MONEY));
		}
	}

	public static Money valueOf(long money) {
		return new Money(money);
	}

	public LottoCount calculatePurchaseCount() {
		return LottoCount.valueOf((int)(money / LottoTicket.PRICE));
	}

	public Money multiply(long multiplier) {
		return Money.valueOf(money * multiplier);
	}

	public Money plus(Money other) {
		return Money.valueOf(money + other.money);
	}

	public long calculatePercentage(Money other) {
		if (other.money == MINIMUM_MONEY) {
			return DEFAULT_PERCENTAGE;
		}
		return money * PROFIT_PERCENTAGE / other.money;
	}

	public long getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Money that = (Money)object;
		return this.money == that.money;
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
