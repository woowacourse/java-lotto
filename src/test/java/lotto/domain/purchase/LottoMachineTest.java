package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoTicket.LottoTickets;

class LottoMachineTest {

	@Test
	void LottoMachine_InputManualLottoTickets_GenerateInstance() {
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);
		assertThat(new LottoMachine(inputLottoTickets)).isInstanceOf(LottoMachine.class);
	}

	@Test
	void purchaseLottoTickets_TotalPurchasingCount_GenerateLottoTickets() {
		LottoMoney lottoMoney = new LottoMoney(15_000);
		TotalPurchasingCount totalPurchasingCount = TotalPurchasingCount.from("3", lottoMoney);
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);
		LottoMachine lottoMachine = new LottoMachine(inputLottoTickets);

		assertThat(lottoMachine.purchaseLottoTickets(totalPurchasingCount)).isInstanceOf(LottoTickets.class);
	}

}
