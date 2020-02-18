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
}
