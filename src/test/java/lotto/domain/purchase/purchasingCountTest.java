package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class purchasingCountTest {
	@Test
	void PurchasingCount_InputPurchasingCount_GenerateInstance() {
		int value = 10;

		assertThat(new PurchasingCount(value)).isInstanceOf(PurchasingCount.class);
	}

	@Test
	void validate_NegativeNumber_InvalidPurchasingCountExceptionThrown() {
		int value = -1;

		assertThatThrownBy(() -> new PurchasingCount(value))
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.INVALID);
	}

	@Test
	void isOverBy_BiggerNumberThanPurchasingCount_ReturnTrue() {
		PurchasingCount purchasingCount = new PurchasingCount(10);
		int compareValue = 15;

		boolean actual = purchasingCount.isOverBy(compareValue);

		assertThat(actual).isTrue();
	}

	@Test
	void purchaseFor_ManualLottoTicketCount_MinusManualLottoTicketCount() {
		int purchasingCountValue = 10;
		PurchasingCount purchasingCount = new PurchasingCount(purchasingCountValue);
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount("5", purchasingCount);

		purchasingCount.purchaseFor(manualLottoTicketCount);

		assertThat(purchasingCount.getPurchasingCount()).isEqualTo(
			purchasingCountValue - manualLottoTicketCount.getManualLottoTicketCount());
	}
}
