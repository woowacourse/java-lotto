package lotto.domain;

import lotto.exceptions.PurchaseManualTicketIllegalArgumentException;
import lotto.exceptions.PurchaseMoneyIllegalArgumentException;

import java.util.Objects;

public class PurchaseMoney {
	public static final int POSITIVE_THRESHOLD = 0;
	private static final int TICKET_PRICE = 1000;
	private static final int VALID_REMAINDER = 0;

	private final int purchaseMoney;

	public PurchaseMoney(final int purchaseMoney) {
		checkIsLessThanZero(purchaseMoney);
		checkIsMultipleOfThousand(purchaseMoney);

		this.purchaseMoney = purchaseMoney;
	}

	private void checkIsMultipleOfThousand(int purchaseMoney) {
		if (purchaseMoney % TICKET_PRICE != VALID_REMAINDER) {
			throw new PurchaseMoneyIllegalArgumentException(purchaseMoney);
		}
	}

	private void checkIsLessThanZero(int purchaseMoney) {
		if (purchaseMoney < POSITIVE_THRESHOLD) {
			throw new PurchaseMoneyIllegalArgumentException(purchaseMoney);
		}
	}

	public int countPurchasedTickets() {
		return purchaseMoney / TICKET_PRICE;
	}

	public double divideBy(double totalEarning) {
		return totalEarning / purchaseMoney;
	}

	public void checkCanBuy(int ticketCount) {
		if (ticketCount < POSITIVE_THRESHOLD || ticketCount > countPurchasedTickets()) {
			throw new PurchaseManualTicketIllegalArgumentException();
		}
	}

	public int getMoney() {
		return purchaseMoney;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchaseMoney that = (PurchaseMoney) o;
		return purchaseMoney == that.purchaseMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchaseMoney);
	}
}
