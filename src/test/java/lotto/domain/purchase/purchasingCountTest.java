package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
	void valueOf_InputPurchasingCount_ReturnInstance() {
		String inputPurchasingCount = "5";

		assertThat(PurchasingCount.valueOf(inputPurchasingCount)).isInstanceOf(PurchasingCount.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "123.4"})
	void parseToInt_NotIntegerInputPurchasingCount_InvalidPurchasingCountExceptionThrown(String value) {
		assertThatThrownBy(() -> PurchasingCount.valueOf(value))
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.NOT_INTEGER);
	}

	@Test
	void subtract_ManualPurchasingCount_TotalPurchasingCountMinusManualPurchasingCount() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(10);
		PurchasingCount manualPurchasingCount = PurchasingCount.valueOf("5");

		PurchasingCount expected = new PurchasingCount(5);

		assertThat(totalPurchasingCount.subtract(manualPurchasingCount)).isEqualTo(expected);
	}

	@Test
	void subtract_InvalidManualPurchasingCount_InvalidPurchasingCountExceptionThrown() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(10);
		PurchasingCount manualPurchasingCount = PurchasingCount.valueOf("15");

		assertThatThrownBy(() -> totalPurchasingCount.subtract(manualPurchasingCount))
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.MANUAL_OVER);
	}

	@ParameterizedTest
	@ValueSource(ints = {5, 10})
	void buyLottoTicket_ReducePurchasingCountByOne(int value) {
		PurchasingCount totalPurchasingCount = new PurchasingCount(value);
		totalPurchasingCount.buyLottoTicket();

		PurchasingCount expected = new PurchasingCount(value - 1);
		assertThat(totalPurchasingCount).isEqualTo(expected);
	}

	@Test
	void buyLottoTicket_ReducePurchasingCountUntilNegative_InvalidPurchasingCountExceptionThrown() {
		PurchasingCount totalPurchasingCount = new PurchasingCount(0);

		assertThatThrownBy(totalPurchasingCount::buyLottoTicket)
			.isInstanceOf(InvalidPurchasingCountException.class)
			.hasMessage(InvalidPurchasingCountException.INVALID);
	}

	@Test
	void isAvailableForPurchase_PurchasingCountOverThanZero_ReturnTrue() {
		PurchasingCount purchasingCount = new PurchasingCount(1);

		assertThat(purchasingCount.isAvailableForPurchase()).isTrue();
	}

	@Test
	void isAvailableForPurchase_PurchasingCountLessOrEqualThanZero_ReturnFalse() {
		PurchasingCount purchasingCount = new PurchasingCount(0);

		assertThat(purchasingCount.isAvailableForPurchase()).isFalse();
	}

}
