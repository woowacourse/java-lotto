package lotto.domain;

import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {
	@Test
	void PurchaseMoney() {
		// given
		int input = 1000;

		// when
		LottoMoney lottoMoney = new LottoMoney(1000);

		// then
		Assertions.assertThat(lottoMoney.getPurchaseMoney())
				.isEqualTo(1000);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
	void PurchaseMoney_LessThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new LottoMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void PurchaseMoney_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new LottoMoney(input);
		}).isInstanceOf(PurchaseMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + PurchaseMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000", "10000,9000", "10000,0"})
	void subtractByTicketNumber(int amount, int input) {
		// given
		LottoMoney lottoMoney = new LottoMoney(amount);

		// when
		LottoMoney other = new LottoMoney(input);
		LottoMoney result = lottoMoney.subtract(other);

		// then
		Assertions.assertThat(result)
				.isEqualTo(new LottoMoney(amount - input));
	}

	@Test
	void subtractByTicketNumber_BiggerThanPurchaseMoney_ShouldThrowException() {
		// given
		int amount = 10000;
		LottoMoney lottoMoney = new LottoMoney(amount);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney other = new LottoMoney(11000);
			LottoMoney result = lottoMoney.subtract(other);
		});
	}

	@Test
	void of() {
		// given
		int input = 10;

		// when
		LottoMoney result = LottoMoney.of(input);

		// then
		LottoMoney expected = new LottoMoney(input * LottoMoney.LOTTO_PRICE);
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

}
