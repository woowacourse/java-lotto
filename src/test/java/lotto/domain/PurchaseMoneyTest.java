package lotto.domain;

import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseMoneyTest {
	@Test
	void PurchaseMoney() {
		// given
		int input = 1000;

		// when
		PurchaseMoney purchaseMoney = new PurchaseMoney(1000);

		// then
		Assertions.assertThat(purchaseMoney.getMoney())
				.isEqualTo(1000);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
	void PurchaseMoney_LessOrEqualThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new PurchaseMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+는 유효한 금액이 아닙니다. 금액은 0보다 큰 1000의 배수여야 합니다.");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void PurchaseMoney_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new PurchaseMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+는 유효한 금액이 아닙니다. 금액은 0보다 큰 1000의 배수여야 합니다.");
	}
}
