package lotto.domain;

import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseMoneyTest {
	@Test
	void PurchaseMoney() {
		// given
		int input = 1000;

		// when
		PurchaseMoney purchaseMoney = new PurchaseMoney(1000);

		// then
		Assertions.assertThat(purchaseMoney.getPurchaseMoney())
				.isEqualTo(1000);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
	void PurchaseMoney_LessThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new PurchaseMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void PurchaseMoney_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new PurchaseMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000", "10000,9000", "10000,0"})
	void subtractByTicketNumber(int amount, int input) {
		// given
		PurchaseMoney purchaseMoney = new PurchaseMoney(amount);

		// when
		PurchaseMoney other = new PurchaseMoney(input);
		PurchaseMoney result = purchaseMoney.subtract(other);

		// then
		Assertions.assertThat(result)
				.isEqualTo(new PurchaseMoney(amount - input));
	}

	@Test
	void subtractByTicketNumber_BiggerThanPurchaseMoney_ShouldThrowException() {
		// given
		int amount = 10000;
		PurchaseMoney purchaseMoney = new PurchaseMoney(amount);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			PurchaseMoney other = new PurchaseMoney(11000);
			PurchaseMoney result = purchaseMoney.subtract(other);
		});
	}

	@Test
	void of() {
	}

}
