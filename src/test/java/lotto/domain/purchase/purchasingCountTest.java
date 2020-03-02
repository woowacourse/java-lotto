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

}
