package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

	@ParameterizedTest
	@ValueSource(ints = {1000, 50000, 100000})
	void testValidMoney(final int money) {
		assertThatNoException().isThrownBy(() ->
			new Money(money)
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 999, 100001})
	void testInvalidMoney(final int money) {
		assertThatThrownBy(() ->
			new Money(money)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("구입 금액의 범위는 1000원~100000원 입니다.");
	}
}
