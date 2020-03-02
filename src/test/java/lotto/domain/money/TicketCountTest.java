package lotto.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketCountTest {
	@Test
	void TicketCount() {
		// given
		int manualTicketCount= 3;
		int autoTicketCount = 7;

		// when
		TicketCount ticketCount = new TicketCount(manualTicketCount, autoTicketCount);

		// then
		Assertions.assertThat(ticketCount).isEqualTo(new TicketCount(manualTicketCount, autoTicketCount));
	}
}
