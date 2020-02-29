package lotto.domain.money;

import lotto.exceptions.MoneyIllegalException;
import lotto.exceptions.TicketCountIllegalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
	@Test
	void Money() {
		// given
		int input = 1000;

		// when
		Money money = new Money(input);

		// then
		Assertions.assertThat(money).isEqualTo(new Money(1000));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -1000, Integer.MIN_VALUE})
	void Money_Negative_ThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new Money(input);
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
			new Money(input);
		}).isInstanceOf(MoneyIllegalException.class)
				.hasMessageMatching("-?[0-9]+는 적절하지 않은 금액입니다.\n" +
						" - 0 이상의 1000의 배수로 입력해주세요.");
	}

	@ParameterizedTest
	@CsvSource(value = {"5000,6", "0,1", "3000,10", "2000, -1"})
	void checkCanBuy_NotPurchaseAvailable_ThrowException(int input, int given) {
		// given
		Money money = new Money(input);

		// then
		Assertions.assertThatThrownBy(() -> {
			money.checkCanBuy(given);
		}).isInstanceOf(TicketCountIllegalException.class)
				.hasMessageMatching("구매한 금액 [0-9]+로 -?[0-9]+개의 티켓을 구매할 수 없습니다.\n" +
						" - 티켓 갯수는 0 이상의 정수여야합니다.\n" +
						" - 구매한 금액으로는 최대 [0-9]+개의 티켓을 구매하실 수 있습니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"0,0", "1000,1", "10000,10"})
	void totalTicketCount(int input, int expected) {
		// given
		Money money = new Money(input);

		// when
		int result = money.totalTicketCount();

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,10000,1000", "2000,1000,50", "3000,900,30", "4000,4000,100"})
	void calculateProfitRate(int money, double totalProfit, double expected) {
		// given
		Money given = new Money(money);

		// when
		double result = given.calculateProfitRate(totalProfit);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}
}