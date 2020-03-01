package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoTicket.LottoTickets;

class LottoMachineTest {

	@Test
	void LottoMachine_PurchasingCountAndManualLottoTicketCount_GenerateInstance() {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("5", purchasingCount);

		assertThat(new LottoMachine(purchasingCount, manualLottoTicketCount)).isInstanceOf(LottoMachine.class);
	}

	@Test
	void getNumberOfManualAndAutoLottoTickets_ReturnListOfAutoAndManualLottoTicketCount() {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("5", purchasingCount);
		LottoMachine lottoMachine = new LottoMachine(purchasingCount, manualLottoTicketCount);

		List<Integer> expected = Arrays.asList(5, 5);

		assertThat(lottoMachine.getNumberOfManualAndAutoLottoTickets()).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 5, 10})
	void purchaseLottoTicketsManualAndAutoBy_InputManualLottoTickets_GenerateLottoTickets(int value) {
		PurchasingCount purchasingCount = new PurchasingCount(value);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("1", purchasingCount);
		LottoMachine lottoMachine = new LottoMachine(purchasingCount, manualLottoTicketCount);

		LottoTickets lottoTickets = lottoMachine.purchaseLottoTicketsManualAndAutoBy(Arrays.asList("1, 2, 3, 4, 5, 6"));

		assertThat(lottoTickets.getLottoTicketsSize()).isEqualTo(value);
	}

	@Test
	void purchaseLottoTicketsManualAndAutoBy_InvalidSizeOfInputManualLottoTickets_InvalidLottoMachineExceptionThrown() {
		int value = 2;
		PurchasingCount purchasingCount = new PurchasingCount(value);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("1", purchasingCount);
		LottoMachine lottoMachine = new LottoMachine(purchasingCount, manualLottoTicketCount);

		assertThatThrownBy(() -> lottoMachine.purchaseLottoTicketsManualAndAutoBy(Arrays.asList(
			"1, 2, 3, 4, 5, 6",
			"1, 2, 3, 4, 5, 8")))
			.isInstanceOf(InvalidLottoMachineException.class)
			.hasMessage(InvalidLottoMachineException.INVALID);
	}

}
