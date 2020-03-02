package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.purchase.LottoMoney;
import lotto.domain.purchase.TotalPurchasingCount;

class ManualLottoTicketsFactoryTest {
	@Test
	void generate_InputLottoTickets_GenerateLottoTicketList() {
		LottoMoney lottoMoney = new LottoMoney(15_000);
		TotalPurchasingCount totalPurchasingCount = TotalPurchasingCount.from("3", lottoMoney);
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);
		LottoTicketsGeneratable lottoTicketsFactory = new ManualLottoTicketsFactory(inputLottoTickets);

		assertThat(lottoTicketsFactory.generate(totalPurchasingCount.getManualPurchasingCount()))
			.isInstanceOf(LottoTickets.class);
	}
}
