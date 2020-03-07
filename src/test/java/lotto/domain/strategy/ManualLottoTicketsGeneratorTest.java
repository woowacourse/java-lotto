package lotto.domain.strategy;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

class ManualLottoTicketsGeneratorTest {

	@Test
	void generate_InputLottoTickets_GenerateLottoTicketsInstance() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(3);
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);
		LottoTicketsGenerator lottoTicketsFactory = new ManualLottoTicketsGenerator(inputLottoTickets);

		assertThat(lottoTicketsFactory.generate(totalPurchasingCount)).isInstanceOf(LottoTickets.class);
	}

}
