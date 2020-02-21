package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

	@DisplayName("돈 받아서 1000원으로 떨어지는지 검증")
	@ParameterizedTest
	@ValueSource(ints = {1001, 999, 10010, -10, 0, -1000})
	void validDivideByThousand(int money) {
		assertThatThrownBy(() -> new Money(money))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,1", "10000,10"})
	void calculateLottoCount(int moneyValue, int expected) {
		Money money = new Money(moneyValue);
		assertThat(money.findLottoTicketCount()).extracting("lottoCount").isEqualTo(expected);
	}
}
