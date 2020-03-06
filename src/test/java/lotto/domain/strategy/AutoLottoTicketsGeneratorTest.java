package lotto.domain.strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

class AutoLottoTicketsGeneratorTest {

	@Test
	void generate_totalPurchasingCount_GenerateLottoTicketsInstance() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(10);
		LottoTicketsGenerator lottoTicketsFactory = new AutoLottoTicketsGenerator(LottoNumberCache.values());

		assertThat(lottoTicketsFactory.generate(totalPurchasingCount)).isInstanceOf(LottoTickets.class);
	}

}
