package lotto.domain.money;

import lotto.exceptions.MoneyIllegalException;
import lotto.exceptions.TicketCountIllegalException;

import java.util.Objects;

public class Money {
	private static final int POSITIVE_THRESHOLD = 0;
	private static final int VALID_REMAINDER = 0;
	private static final int LOTTO_TICKET_PRICE = 1000;
	private static final int PERCENT_MULTIPLIER = 100;

	private final int money;

	public Money(final int money) {
		checkIsPositive(money);
		checkIsMultipleOfOneThousand(money);

		this.money = money;
	}

	private void checkIsPositive(int money) {
		if (money < POSITIVE_THRESHOLD) {
			throw new MoneyIllegalException(money);
		}
	}

	private void checkIsMultipleOfOneThousand(int money) {
		if (money % LOTTO_TICKET_PRICE != VALID_REMAINDER) {
			throw new MoneyIllegalException(money);
		}
	}

	public void checkCanBuy(int ticketCount) {
		if (ticketCount < POSITIVE_THRESHOLD || ticketCount > totalTicketCount()) {
			throw new TicketCountIllegalException(money, ticketCount, totalTicketCount());
		}
	}

	public int totalTicketCount() {
		return money / LOTTO_TICKET_PRICE;
	}

	public double calculateProfitRate(double totalProfit) {
		return totalProfit / money * PERCENT_MULTIPLIER;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Money money1 = (Money) o;
		return money == money1.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
