package lotto.domain.strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

class AutoLottoTicketsGeneratorTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 4, 7, 10})
	void generate_NumberOfLottoTicket_GenerateLottoTicketsInstance(int value) {
		PurchasingCount purchasingCount = new PurchasingCount(value);
		LottoTicketsGenerator lottoTicketsFactory = new AutoLottoTicketsGenerator(LottoNumberCache.values());

		assertThat(lottoTicketsFactory.generate(purchasingCount)).isInstanceOf(LottoTickets.class);
	}

}
