package lotto.domain.ticket;

import java.util.Objects;

public final class Money {
	private static final int PROFIT_PERCENTAGE = 100;
	private static final int MINIMUM_MONEY = 0;
	private static final String NOT_GREATER_THAN_MINIMUM_EXCEPTION_MESSAGE = String.format("%d 보다 작은 값은 받을 수 없습니다.",
		MINIMUM_MONEY);
	public static final long UNIT = 1000;

	private final long money;

	private Money(long money) {
		validateGreaterThanMinimum(money);
		this.money = money;
	}

	private void validateGreaterThanMinimum(long money) {
		if (money < MINIMUM_MONEY) {
			throw new IllegalArgumentException(NOT_GREATER_THAN_MINIMUM_EXCEPTION_MESSAGE);
		}
	}

	public static Money valueOf(long money) {
		return new Money(money);
	}

	public LottoCount calculatePurchaseCount() {
		return LottoCount.valueOf((int)(money / UNIT));
	}

	public Money multiply(long multiplier) {
		return Money.valueOf(money * multiplier);
	}

	public Money plus(Money other) {
		return Money.valueOf(money + other.money);
	}

	public long calculateProfitRate(Money lottoPrice) {
		if (lottoPrice.money == MINIMUM_MONEY) {
			return 0;
		}
		return money * PROFIT_PERCENTAGE / lottoPrice.money;
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
