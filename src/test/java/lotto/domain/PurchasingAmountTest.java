package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PurchasingAmountTest {
	@Test
	void IllegalArgumentExceptionWhenInputHasChangeMoney() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(1500))
			.withMessage("구입 금액은 1000원 단위이어야 합니다.");
	}

	@Test
	void IllegalArgumentExceptionWhenInputNegativeValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(-1000))
			.withMessage("구입 금액은 음수가 될 수 없습니다.");
	}

	@Test
	void IllegalArgumentExceptionWhenInputZeroValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(0))
			.withMessage("최소 한장이상 구매 하셔야 합니다.");
	}
}
