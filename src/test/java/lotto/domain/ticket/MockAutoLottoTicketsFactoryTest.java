package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MockAutoLottoTicketsFactoryTest {
	@Test
	void create() {
		// given
		int autoTicketCount = 3;
		MockAutoLottoTicketsFactory autoLottoTicketsFactory = MockAutoLottoTicketsFactory.of(autoTicketCount);

		// when
		LottoTickets result = LottoTickets.of(autoLottoTicketsFactory);

		// then
		String expectedString = "1,2,3,4,5,6";
		List<SerialLottoNumber> expected = Stream.of(expectedString, expectedString, expectedString)
				.map(SerialLottoNumber::of)
				.collect(Collectors.toList());
		Assertions.assertThat(result).isEqualTo(new LottoTickets(expected));
	}
}