package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
	@ParameterizedTest
	@ValueSource(ints = {100, 1001, 100001})
	@DisplayName("투입 금액이 천원 단위가 아닌 경우")
	void validateNotThousandUnitInputMoney(int number) {
		assertThatThrownBy(() -> new Money(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 금액은 천원 단위여야 합니다.");
	}

	@Test
	@DisplayName("투입 금액이 천원 단위가 아닌 경우(0원)")
	void validateInputMoneyZero() {
		assertThatThrownBy(() -> new Money(0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 금액은 천원 단위여야 합니다.");
	}

	@Test
	@DisplayName("투입 금액이 천원 단위 경우 갯수 반환")
	void validateThousandUnitInputMoney() {
		Money money = new Money(10000);

		assertThat(money.makeMoneyToCount()).isEqualTo(10);
	}
}
