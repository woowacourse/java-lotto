package domain;

public class TicketCounter {
	private static final int ZERO_COUNT = 0;

	private static final String INVALID_MONEY_ERROR = "구입 금액은 1000원 이상이어야 합니다.";
	private static final String NEGATIVE_COUNT_ERROR = "구매할 로또 수는 음수일 수 없습니다.";
	private static final String OVER_COUNT_ERROR = "구매할 로또 수는 구입 금액으로 살 수 있어야 합니다.";

	private final int autoCount;

	public TicketCounter(final int money, final int manualCount) {
		validateMoney(money);
		validateManualCount(money, manualCount);

		this.autoCount = (money - (manualCount * Ticket.PRICE)) / Ticket.PRICE;
	}

	private void validateMoney(final int money) {
		if (money < Ticket.PRICE) {
			throw new IllegalArgumentException(INVALID_MONEY_ERROR);
		}
	}

	private void validateManualCount(final int money, final int manualCount) {
		if (manualCount < ZERO_COUNT) {
			throw new IllegalArgumentException(NEGATIVE_COUNT_ERROR);
		}

		if (manualCount * Ticket.PRICE > money) {
			throw new IllegalArgumentException(OVER_COUNT_ERROR);
		}
	}

	public int getAutoCount() {
		return autoCount;
	}
}
