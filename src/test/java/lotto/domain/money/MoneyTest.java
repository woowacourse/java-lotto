package lotto.domain.money;

import lotto.exceptions.MoneyIllegalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
	@Test
	void of() {
		// given
		int input = 1000;

		// when
		Money money = Money.of(input);

		// then
		Assertions.assertThat(money).isEqualTo(Money.of(1000));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -1000, Integer.MIN_VALUE})
	void Money_Negative_ThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Money.of(input);
		}).isInstanceOf(MoneyIllegalException.class)
				.hasMessageMatching("-?[0-9]+는 적절하지 않은 금액입니다.\n" +
						" - 0 이상의 1000의 배수로 입력해주세요.");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 500, 999, 1200, 1350, 9001})
	void Money_NotMultipleOfOneThousand_ThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Money.of(input);
		}).isInstanceOf(MoneyIllegalException.class)
				.hasMessageMatching("-?[0-9]+는 적절하지 않은 금액입니다.\n" +
						" - 0 이상의 1000의 배수로 입력해주세요.");
	}

	@ParameterizedTest
	@CsvSource(value = {"0,0", "1000,1", "10000,10"})
	void totalTicketCount(int input, int expected) {
		// given
		Money money = Money.of(input);

		// when
		int result = money.totalTicketCount();

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,10000,1000", "2000,1000,50", "3000,900,30", "4000,4000,100"})
	void calculateProfitRate(int money, double totalProfit, double expected) {
		// given
		Money given = Money.of(money);

		// when
		double result = given.calculateProfitRate(totalProfit);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}
}