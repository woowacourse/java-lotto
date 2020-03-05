package lotto.domain;

import lotto.exceptions.LottoMoneyIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {
	@Test
	void of() {
		// given
		int input = 1000;

		// when
		LottoMoney lottoMoney = LottoMoney.of(input);

		// then
		Assertions.assertThat(lottoMoney.getPurchaseMoney())
				.isEqualTo(input);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
	void of_LessThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney.of(input);
		}).isInstanceOf(LottoMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoMoneyIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void of_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney.of(input);
		}).isInstanceOf(LottoMoneyIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoMoneyIllegalArgumentException.MESSAGE);
	}

	@Test
	void ofLottoCount() {
		// given
		int input = 10;

		// when
		LottoMoney result = LottoMoney.ofLottoCount(input);

		// then
		LottoMoney expected = LottoMoney.of(input * LottoMoney.LOTTO_PRICE);
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000", "10000,9000", "10000,0"})
	void subtractByTicketNumber(int amount, int input) {
		// given
		LottoMoney lottoMoney = LottoMoney.of(amount);

		// when
		LottoMoney other = LottoMoney.of(input);
		LottoMoney result = lottoMoney.subtract(other);

		// then
		Assertions.assertThat(result)
				.isEqualTo(LottoMoney.of(amount - input));
	}

	@Test
	void subtractByTicketNumber_BiggerThanPurchaseMoney_ShouldThrowException() {
		// given
		int amount = 10000;
		LottoMoney lottoMoney = LottoMoney.of(amount);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney other = LottoMoney.of(11000);
			LottoMoney result = lottoMoney.subtract(other);
		});
	}

	@Test
	void isInvalidInstance_ShouldReturnTrue() {
		// given
		LottoMoney given = LottoMoney.invalidInstance();

		// when
		boolean result = given.isInvalidInstance();

		// then
		Assertions.assertThat(result)
				.isTrue();
	}

	@Test
	void isInvalidInstance_ShouldReturnFalse() {
		// given
		LottoMoney given = LottoMoney.of(1000);

		// when
		boolean result = given.isInvalidInstance();

		// then
		Assertions.assertThat(result)
				.isFalse();
	}
}
