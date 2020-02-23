package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AutoLottoTicketsFactoryTest {
	@ParameterizedTest
	@ValueSource(longs = {1, 4, 7, 10})
	void generate_NumberOfLottoTicket_ReturnLottoTickets(long value) {
		LottoTickets actual = AutoLottoTicketsFactory.generate(value);

		assertThat(actual.getLottoTickets().size()).isEqualTo(value);
	}
}
