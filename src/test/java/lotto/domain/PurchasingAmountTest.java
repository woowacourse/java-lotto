package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchasingAmountTest {
	@DisplayName("생성자 테스트 - 1000원 단위가 아닌 경우")
	@Test
	void IllegalArgumentExceptionWhenInputHasChangeMoney() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(1500))
			.withMessage("구입 금액은 1000원 단위이어야 합니다.");
	}

	@DisplayName("생성자 테스트 - 음수인 경우")
	@Test
	void IllegalArgumentExceptionWhenInputNegativeValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(-1000))
			.withMessage("구입 금액은 음수가 될 수 없습니다.");
	}

	@DisplayName("생성자 테스트 - 최소 금액에 도달하지 못한 경우")
	@Test
	void IllegalArgumentExceptionWhenInputZeroValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new PurchasingAmount(0))
			.withMessage("최소 한장이상 구매 하셔야 합니다.");
	}
}
