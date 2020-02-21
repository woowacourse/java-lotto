package lotto.domain;

import java.util.Objects;

public class Money {
	private static final int ZERO = 0;
	private static final long LOTTO_PRICE = 1000;
	private static final String MONEY_EXCEPTION_MESSAGE = String.format("금액을 %d원 단위로 입력해주세요.", LOTTO_PRICE);
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
		if (money < ZERO) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	private void validateDivideByThousand(long money) {
		if (money % LOTTO_PRICE != ZERO) {
			throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
		}
	}

	public static Money of(long money) {
		return new Money(money);
	}

	public LottoCount findLottoTicketCount() {
		return LottoCount.of((int)(money / LOTTO_PRICE));
	}

	public Money multiply(long multiplier) {
		return Money.of(money * multiplier);
	}

	public Money plus(Money otherMoney) {
		return Money.of(money + otherMoney.money);
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
