package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalPurchasingCountTest {

	@Test
	void from_InputManualPurchasingCountAndLottoMoney_GenerateInstance() {
		String inputManualPurchasingCount = "5";
		LottoMoney lottoMoney = new LottoMoney(15_000L);

		assertThat(TotalPurchasingCount.from(inputManualPurchasingCount, lottoMoney)).isInstanceOf(
			TotalPurchasingCount.class);
	}

	@Test
	void from_InputManualPurchasingCountOverThanPurchasableCount_InvalidPurchasingCountExceptionThrown() {
		String inputManualPurchasingCount = "10";
		LottoMoney lottoMoney = new LottoMoney(1_000L);

		assertThatThrownBy(() -> TotalPurchasingCount.from(inputManualPurchasingCount, lottoMoney))
			.isInstanceOf(InvalidPurchasingCountException.class);
	}

}
