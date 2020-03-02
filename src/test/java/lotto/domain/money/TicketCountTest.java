package lotto.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TicketCountTest {
	@Test
	void TicketCount() {
		// given
		int manualTicketCount = 3;
		int autoTicketCount = 7;

		// when
		TicketCount ticketCount = new TicketCount(manualTicketCount, autoTicketCount);

		// then
		Assertions.assertThat(ticketCount).isEqualTo(new TicketCount(manualTicketCount, autoTicketCount));
	}

	@ParameterizedTest
	@CsvSource(value = {"10,3", "11,4", "4,4"})
	void of(int totalTicketCount, int manualTicketCount) {
		// when
		TicketCount ticketCount = TicketCount.of(totalTicketCount, manualTicketCount);

		// then
		Assertions.assertThat(ticketCount)
				.isEqualTo(new TicketCount(manualTicketCount, totalTicketCount - manualTicketCount));
	}
}
