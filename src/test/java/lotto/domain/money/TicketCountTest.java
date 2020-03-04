package lotto.domain.money;

import lotto.exceptions.TicketCountIllegalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TicketCountTest {
	@ParameterizedTest
	@CsvSource(value = {"10,3", "11,4", "4,4"})
	void of(int totalTicketCount, int manualTicketCount) {
		// when
		TicketCount ticketCount = TicketCount.of(totalTicketCount, manualTicketCount);

		// then
		Assertions.assertThat(ticketCount.getAutoTicketCount()).isEqualTo(totalTicketCount - manualTicketCount);
		Assertions.assertThat(ticketCount.getManualTicketCount()).isEqualTo(manualTicketCount);
	}

	@ParameterizedTest
	@CsvSource(value = {"3,10", "3,4", "10,-1", "-1, 10"})
	void of_CanNotBuy_ThrowException(int totalTicketCount, int manualTicketCount) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			TicketCount.of(totalTicketCount, manualTicketCount);
		}).isInstanceOf(TicketCountIllegalException.class)
				.hasMessageMatching("티켓 매수가 유효하지 않습니다.\n" +
						" - 티켓 매수는 0 이상의 정수여야합니다.\n" +
						" - 수동으로 구매할 티켓 매수는 전체 티켓 매수를 초과할 수 없습니다.");
	}
}
