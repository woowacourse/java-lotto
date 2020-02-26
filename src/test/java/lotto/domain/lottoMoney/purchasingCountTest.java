package lotto.domain.lottoMoney;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoTicket.ManualLottoTicketCount;

class purchasingCountTest {
	@Test
	void PurchasingCount_InputPurchasingCount_GenerateInstance() {
		long value = 10;

		assertThat(new PurchasingCount(value)).isInstanceOf(PurchasingCount.class);
	}

	@Test
	void validate_NegativeNumber_InvalidPurchasingCountExceptionThrown() {
		long value = -1;

		assertThatThrownBy(() -> new PurchasingCount(value))
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.INVALID);
	}

	@Test
	void isOverBy_BiggerNumberThanPurchasingCount_ReturnTrue() {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		long compareValue = 15;

		boolean actual = purchasingCount.isOverBy(compareValue);

		assertThat(actual).isTrue();
	}

	@Test
	void useFor_ManualLottoTicketCount_MinusManualLottoTicketCount() {
		long purchasingCountValue = 10;
		PurchasingCount purchasingCount = new PurchasingCount(purchasingCountValue);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("5", purchasingCount);

		purchasingCount.useFor(manualLottoTicketCount);

		assertThat(purchasingCount.getPurchasingCount()).isEqualTo(
			purchasingCountValue - manualLottoTicketCount.getManualLottoTicketCount());
	}
}
