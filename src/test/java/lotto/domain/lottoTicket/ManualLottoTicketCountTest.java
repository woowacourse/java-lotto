package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoMoney.PurchasingCount;

class ManualLottoTicketCountTest {
	@ParameterizedTest
	@ValueSource(strings = {"0", "5"})
	void ManualLottoTicketNumber_ValidNumber_GenerateInstance(String value) {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		assertThat(new ManualLottoTicketCount(value, purchasingCount)).isInstanceOf(ManualLottoTicketCount.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "123.4"})
	void parseToInt_NotIntegerNumber_InvalidManualLottoTicketCountExceptionThrown(String value) {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		assertThatThrownBy(() -> new ManualLottoTicketCount(value, purchasingCount))
			.isInstanceOf(InvalidManualLottoTicketCountException.class)
			.hasMessage(InvalidManualLottoTicketCountException.NOT_INTEGER);
	}

	@Test
	void validateNegative_NegativeNumber_InvalidManualLottoTicketCountExceptionThrown() {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		String value = "-1";
		assertThatThrownBy(() -> new ManualLottoTicketCount(value, purchasingCount))
			.isInstanceOf(InvalidManualLottoTicketCountException.class)
			.hasMessage(InvalidManualLottoTicketCountException.NEGATIVE);
	}

	@ParameterizedTest
	@ValueSource(strings = {"16", "17", "18"})
	void validatePurchasable_OverThanPurchasableCount_InvalidManualLottoTicketCountExceptionThrown(String value) {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		assertThatThrownBy(() -> new ManualLottoTicketCount(value, purchasingCount))
			.isInstanceOf(InvalidManualLottoTicketCountException.class)
			.hasMessage(InvalidManualLottoTicketCountException.NOT_PURCHASABLE);
	}
}
