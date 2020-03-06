package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.strategy.AutoLottoTicketsGenerator;
import lotto.domain.strategy.LottoTicketsGenerator;
import lotto.domain.strategy.ManualLottoTicketsGenerator;

class LottoMachineTest {

	private List<LottoTicketsGenerator> lottoTicketsGenerators;

	@BeforeEach
	void setUp() {
		List<String> inputLottoTickets = Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"2, 3, 4, 5, 6, 7",
			"3, 4, 5, 6, 7, 8"
		);

		lottoTicketsGenerators = Arrays.asList(
			new ManualLottoTicketsGenerator(inputLottoTickets),
			new AutoLottoTicketsGenerator(LottoNumberCache.values()));
	}

	@Test
	void LottoMachine_LottoTicketGenerators_GenerateInstance() {
		assertThat(new LottoMachine(lottoTicketsGenerators)).isInstanceOf(LottoMachine.class);
	}

	@Test
	void purchaseLottoTickets_TotalPurchasingCount_GenerateLottoTickets() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(10);

		LottoMachine lottoMachine = new LottoMachine(lottoTicketsGenerators);

		assertThat(lottoMachine.purchaseLottoTickets(totalPurchasingCount)).isInstanceOf(LottoTickets.class);
	}

	@Test
	void purchaseLottoTickets_InvalidTotalPurchasingCount_InvalidPurchasingCountExceptionThrown() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(0);

		LottoMachine lottoMachine = new LottoMachine(lottoTicketsGenerators);

		assertThatThrownBy(() -> lottoMachine.purchaseLottoTickets(totalPurchasingCount))
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.INVALID);
	}

}
