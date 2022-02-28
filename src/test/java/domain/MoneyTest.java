package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 1234})
	@DisplayName("구입금액 : 로또 금액으로 나누어 떨어지지 않는 금액을 입력 한 경우 예외 발생")
	void validatePrice(int inputMoney) {
		assertThatThrownBy(() -> new Money(inputMoney)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동으로 구매할 로또 개수 : 구입금액으로 구입할 수 없는 경우 예외 발생")
	void canNotPurchase() {
		Money money = new Money(5000);
		assertThatThrownBy(() -> money.canPurchase(10)).isInstanceOf(IllegalArgumentException.class);
	}
}
