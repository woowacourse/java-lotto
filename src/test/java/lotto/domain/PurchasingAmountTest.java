package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchasingAmountTest {
	@Test
	@DisplayName("구입 금액 단위가 1000단위가 아닐경우 익셉션을 발생한다")
	void IllegalArgumentExceptionWhenInputHasChangeMoney() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(1500))
			.withMessage("구입 금액은 1000원 단위이어야 합니다.");
	}

	@Test
	@DisplayName("구입 금액이 음수 일경우 익셉션을 발생한다")
	void IllegalArgumentExceptionWhenInputNegativeValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(-1000))
			.withMessage("구입 금액은 음수가 될 수 없습니다.");
	}

	@Test
	@DisplayName("구입 금액이 0원 일경우 익셉션을 발생한다")
	void IllegalArgumentExceptionWhenInputZeroValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(0))
			.withMessage("최소 한장이상 구매 하셔야 합니다.");
}
}
