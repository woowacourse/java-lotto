package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
	@ParameterizedTest
	@ValueSource(strings = {"notNumber", " "})
	@DisplayName("금액이 정수가 아닐 경우 익셉션을 발생시킨다")
	void IllegalArgumentsExceptionWhenInputNotNumber(String input) {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(input))
			.withMessage("금액은 숫자이어야 합니다.");
	}

	@Test
	@DisplayName("공백이 입력될 경우 익셉션을 발생한다")
	void IllegalArgumentsExceptionWhenInputNullOrEmpty() {
		String emptyValue = "";
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(emptyValue))
			.withMessage("금액은 공백이 될 수 없습니다.");
	}
}
