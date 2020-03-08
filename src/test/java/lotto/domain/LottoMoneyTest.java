package lotto.domain;

import lotto.exceptions.LottoMoneyException;
import lotto.exceptions.PurchaseLottoMoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {
	@ParameterizedTest
	@ValueSource(ints = {1000, 2000, 3000})
	void of(int input) {
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
		}).isInstanceOf(LottoMoneyException.class)
				.hasMessageMatching("-?[0-9]+" + LottoMoneyException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 999, 1001, 1500, 2010, 1000100, Integer.MAX_VALUE})
	void of_NotMultipleOfThousand_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney.of(input);
		}).isInstanceOf(LottoMoneyException.class)
				.hasMessageMatching("-?[0-9]+" + LottoMoneyException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 10, 1000})
	void ofLottoCount(int input) {
		// when
		LottoMoney result = LottoMoney.ofLottoCount(input);

		// then
		LottoMoney expected = LottoMoney.of(input * LottoMoney.LOTTO_PRICE);
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -2, -1000})
	void ofLottoCount_LessThanZero_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// then
			LottoMoney result = LottoMoney.ofLottoCount(input);
		}).isInstanceOf(PurchaseLottoMoneyException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000", "10000,9000", "10000,0"})
	void subtractByTicketNumber(int input1, int input2) {
		// given
		LottoMoney lottoMoney1 = LottoMoney.of(input1);
		LottoMoney lottoMoney2 = LottoMoney.of(input2);

		// when
		LottoMoney result = lottoMoney1.subtract(lottoMoney2);

		// then
		Assertions.assertThat(result)
				.isEqualTo(LottoMoney.of(input1 - input2));
	}

	@ParameterizedTest
	@CsvSource(value = {"0, 1000", "1000, 2000", "10000, 11000", "1000, 20000"})
	void subtractByTicketNumber_BiggerThanPurchaseMoney_ShouldThrowException(
			int input1, int input2) {
		// given
		LottoMoney lottoMoney1 = LottoMoney.of(input1);
		LottoMoney lottoMoney2 = LottoMoney.of(input2);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			LottoMoney result = lottoMoney1.subtract(lottoMoney2);
		});
	}
}
