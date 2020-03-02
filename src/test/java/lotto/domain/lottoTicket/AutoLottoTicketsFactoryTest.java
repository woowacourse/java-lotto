package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.purchase.PurchasingCount;

class AutoLottoTicketsFactoryTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 4, 7, 10})
	void generate_NumberOfLottoTicket_ReturnLottoTickets(int value) {
		PurchasingCount purchasingCount = new PurchasingCount(5);
		LottoTicketsGeneratable lottoTicketsFactory = new AutoLottoTicketsFactory(LottoNumberCache.values());

		assertThat(lottoTicketsFactory.generate(purchasingCount)).isInstanceOf(LottoTickets.class);
	}
}
