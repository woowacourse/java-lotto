package lotto.domain.money;

import lotto.exceptions.TicketCountIllegalException;

import java.util.Objects;

public class TicketCount {
	private static final int POSITIVE_THRESHOLD = 0;

	private final int manualTicketCount;
	private final int autoTicketCount;

	private TicketCount(int manualTicketCount, int autoTicketCount) {
		checkTicketCountPositive(manualTicketCount, autoTicketCount);

		this.manualTicketCount = manualTicketCount;
		this.autoTicketCount = autoTicketCount;
	}

	public static TicketCount of(int totalTicketCount, int manualTicketCount) {
		return new TicketCount(manualTicketCount, totalTicketCount - manualTicketCount);
	}

	private static void checkTicketCountPositive(int manualTicketCount, int autoTicketCount) {
		if (manualTicketCount < POSITIVE_THRESHOLD || autoTicketCount < POSITIVE_THRESHOLD) {
			throw new TicketCountIllegalException();
		}
	}

	public int getManualTicketCount() {
		return manualTicketCount;
	}

	public int getAutoTicketCount() {
		return autoTicketCount;
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
