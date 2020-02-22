package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

	@DisplayName("돈 받아서 1000원으로 떨어지는지 검증")
	@ParameterizedTest
	@ValueSource(ints = {1001, 999, 10010, -10, -1000})
	void validDivideByThousand(int money) {
		assertThatThrownBy(() -> Money.valueOf(money))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,1", "10000,10"})
	void calculateLottoCount(int moneyValue, int expected) {
		Money money = Money.valueOf(moneyValue);
		assertThat(money.calculatePurchaseCount()).extracting("lottoCount").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"2000000000,3", "1000,5", "100000000,0"})
	void multiplyTest(long beforeMoney, long multiplier) {
		Money money = Money.valueOf(beforeMoney);
		long expected = beforeMoney * multiplier;
		assertThat(money.multiply(multiplier)).extracting("money").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,1000,2000", "10000,90000,100000", "5000,1000,6000"})
	void plusTest(long firstOperand, long secondOperand, long expected) {
		Money firstMoney = Money.valueOf(firstOperand);
		Money secondMoney = Money.valueOf(secondOperand);
		assertThat(firstMoney.plus(secondMoney)).isEqualTo(Money.valueOf(expected));
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000,100", "2000,10000,20"})
	void findProfits(long prize, int money, long expected) {
		Money prizeValue = Money.valueOf(prize);
		Money moneyValue = Money.valueOf(money);
		assertThat(prizeValue.calculateProfitRate(moneyValue)).isEqualTo(expected);
	}
}
