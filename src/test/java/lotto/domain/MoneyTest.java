package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
	@ParameterizedTest
	@ValueSource(strings = {"notNumber", " "})
	void IllegalArgumentsExceptionWhenInputNotNumber(String input) {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(input))
			.withMessage("금액은 숫자이어야 합니다.");
	}

	@Test
	void IllegalArgumentsExceptionWhenInputNullOrEmpty() {
		String emptyValue = "";
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(emptyValue))
			.withMessage("금액은 공백이 될 수 없습니다.");
	}
}
