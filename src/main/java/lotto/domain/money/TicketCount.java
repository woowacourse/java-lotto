package lotto.domain.money;

import java.util.Objects;

public class TicketCount {
	private final int manualTicketCount;
	private final int autoTicketCount;

	TicketCount(int manualTicketCount, int autoTicketCount) {
		this.manualTicketCount = manualTicketCount;
		this.autoTicketCount = autoTicketCount;
	}

	public static TicketCount of(int totalTicketCount, int manualTicketCount) {
		return new TicketCount(manualTicketCount, totalTicketCount - manualTicketCount);
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
