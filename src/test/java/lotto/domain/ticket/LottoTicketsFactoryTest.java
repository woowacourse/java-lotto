package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketsFactoryTest {
	@Test
	void createByInput() {
		// given
		List<String> input = Arrays.asList("1,2,3,4,5,6",
				"5,6,2,1,8,13",
				"1,2,6,43,45,34");
		ManualLottoTicketsFactory manualLottoTicketsFactory = new ManualLottoTicketsFactory(input);

		// when
		LottoTickets result = LottoTicketsFactory.of(manualLottoTicketsFactory);

		// then
		List<SerialLottoNumber> expected = input.stream()
				.map(SerialLottoNumberFactory::of)
				.collect(Collectors.toList());
		Assertions.assertThat(result).isEqualTo(new LottoTickets(expected));

	}

	@Test
	void createByRandom() {
		// given
		int input = 3;
		MockAutoLottoTicketsFactory autoLottoTicketsFactory = new MockAutoLottoTicketsFactory(input);

		// when
		LottoTickets result = LottoTicketsFactory.of(autoLottoTicketsFactory);

		// then
		String mockTicket = "1,2,3,4,5,6";
		List<SerialLottoNumber> expected = Stream.of(mockTicket, mockTicket, mockTicket)
				.map(SerialLottoNumberFactory::of)
				.collect(Collectors.toList());
		Assertions.assertThat(result).isEqualTo(new LottoTickets(expected));
	}
}
