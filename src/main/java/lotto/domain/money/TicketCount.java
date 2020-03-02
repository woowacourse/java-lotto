package lotto.domain.money;

import lotto.exceptions.TicketCountIllegalException;

import java.util.Objects;

public class TicketCount {
	private static final int POSITIVE_THRESHOLD = 0;
	private final int manualTicketCount;
	private final int autoTicketCount;

	TicketCount(int manualTicketCount, int autoTicketCount) {
		this.manualTicketCount = manualTicketCount;
		this.autoTicketCount = autoTicketCount;
	}

	public static TicketCount of(int totalTicketCount, int manualTicketCount) {
		checkCanBuy(totalTicketCount, manualTicketCount);

		return new TicketCount(manualTicketCount, totalTicketCount - manualTicketCount);
	}

	private static void checkCanBuy(int totalTicketCount, int manualTicketCount) {
		if (totalTicketCount < manualTicketCount || totalTicketCount < POSITIVE_THRESHOLD || manualTicketCount < POSITIVE_THRESHOLD) {
			throw new TicketCountIllegalException();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketCount that = (TicketCount) o;
		return manualTicketCount == that.manualTicketCount &&
				autoTicketCount == that.autoTicketCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(manualTicketCount, autoTicketCount);
	}
}
