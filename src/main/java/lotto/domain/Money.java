package lotto.domain;

import lotto.exceptions.PurchaseMoneyIllegalArgumentException;

import java.math.BigInteger;
import java.util.Objects;

public class Money {
	public static final int POSITIVE_THRESHOLD = 0;
	private static final BigInteger LOTTO_PRICE = new BigInteger("1000");

	private final BigInteger money;

	public Money(final BigInteger money) {
		checkIsLessThanZero(money);
		checkIsMultipleOfThousand(money);

		this.money = money;
	}

	private void checkIsMultipleOfThousand(BigInteger purchaseMoney) {
		if (isNotMultipleOfThousand(purchaseMoney)) {
			throw new PurchaseMoneyIllegalArgumentException(purchaseMoney);
		}
	}

	private boolean isNotMultipleOfThousand(BigInteger purchaseMoney) {
		return !purchaseMoney.mod(LOTTO_PRICE).equals(BigInteger.ZERO);
	}

	private void checkIsLessThanZero(BigInteger purchaseMoney) {
		if (isLessThanZero(purchaseMoney)) {
			throw new PurchaseMoneyIllegalArgumentException(purchaseMoney);
		}
	}

	private boolean isLessThanZero(BigInteger purchaseMoney) {
		return purchaseMoney.compareTo(BigInteger.ZERO) < POSITIVE_THRESHOLD;
	}

	public BigInteger countPurchasedTickets() {
		return money.divide(LOTTO_PRICE);
	}

	public double divideBy(BigInteger totalEarning) {
		return totalEarning.doubleValue() / money.doubleValue();
	}

	public BigInteger getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Money that = (Money) o;
		return money.equals(that.money);
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
