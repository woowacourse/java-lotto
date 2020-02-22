package lotto.domain;

import java.util.Objects;

public class Money {
	private static final long UNIT = 1000;
	private static final String MONEY_EXCEPTION_MESSAGE = String.format("금액을 %d원 단위로 입력해주세요.", UNIT);
	private static final int PROFIT_PERCENTAGE = 100;

	private final long money;

	private Money(long money) {
		validate(money);
		this.money = money;
	}

	private void validate(long money) {
		validatePositive(money);
		validateDivideByThousand(money);
	}

	private void validatePositive(long money) {
		if (money < 0) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	private void validateDivideByThousand(long money) {
		if (money % UNIT != 0) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	public static Money valueOf(long money) {
		return new Money(money);
	}

	public LottoCount calculatePurchaseCount() {
		return LottoCount.of((int)(money / UNIT));
	}

	public Money multiply(long multiplier) {
		return Money.valueOf(money * multiplier);
	}

	public Money plus(Money otherMoney) {
		return Money.valueOf(money + otherMoney.money);
	}

	public long calculateProfitRate(Money lottoPrice) {
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
