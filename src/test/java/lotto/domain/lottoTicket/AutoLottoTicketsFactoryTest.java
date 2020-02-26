package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoMoney.PurchasingCount;

class AutoLottoTicketsFactoryTest {
	@ParameterizedTest
	@ValueSource(longs = {1, 4, 7, 10})
	void generate_NumberOfLottoTicket_ReturnLottoTickets(long value) {
		PurchasingCount purchasingCount = new PurchasingCount(value);

		LottoTickets actual = AutoLottoTicketsFactory.generate(purchasingCount);

		assertThat(actual.getLottoTickets().size()).isEqualTo(value);
	}
}
