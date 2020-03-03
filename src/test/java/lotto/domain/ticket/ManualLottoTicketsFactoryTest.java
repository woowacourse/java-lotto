package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ManualLottoTicketsFactoryTest {
	@Test
	void create() {
		// given
		List<String> lottoTicketsInput = Arrays.asList("1,2,3,4,5,6",
				"3,4,5,6,7,8",
				"43,42,1,3,36,24");
		ManualLottoTicketsFactory manualLottoTicketsFactory
				= ManualLottoTicketsFactory.of(lottoTicketsInput);

		// when
		LottoTickets result = manualLottoTicketsFactory.create();

		// then
		List<SerialLottoNumber> expected = lottoTicketsInput.stream()
				.map(SerialLottoNumber::of)
				.collect(Collectors.toList());
		Assertions.assertThat(result).isEqualTo(new LottoTickets(expected));
	}
}