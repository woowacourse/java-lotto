package lotto.domain;

import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

public class MoneyTest {
	@Test
	void PurchaseMoney() {
		// given
		String input = "1000";

		// when
		Money purchaseMoney = new Money(new BigInteger(input));

		// then
		Assertions.assertThat(purchaseMoney.getMoney())
				.isEqualTo(new BigInteger("1000"));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
	void PurchaseMoney_LessOrEqualThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new Money(new BigInteger(Integer.toString(input)));
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void PurchaseMoney_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new Money(new BigInteger(Integer.toString(input)));
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}
}
