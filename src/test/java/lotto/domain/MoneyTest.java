package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
	@DisplayName("생성자 테스트 - 숫자가 아닌 경우")
	@ParameterizedTest
	@ValueSource(strings = {"notNumber", " "})
	void IllegalArgumentsExceptionWhenInputNotNumber(String input) {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(input))
			.withMessage("금액은 숫자이어야 합니다.");
	}

	@DisplayName("생성자 테스트 - 공백 인 경우")
	@Test
	void IllegalArgumentsExceptionWhenInputNullOrEmpty() {
		String emptyValue = "";
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Money(emptyValue))
			.withMessage("금액은 공백이 될 수 없습니다.");
	}

	@DisplayName("ticket 개수에 대한 Money 생성 기능")
	@Test
	void testTicketPriceOf() {
		Money expected = new Money("1000");
		Money actual = Money.ticketPriceOf(1);
		assertEquals(expected, actual);
	}

	@DisplayName("금액 빼기 기능 테스트")
	@Test
	void testMinus() {
		Money value = new Money("1000");
		Money value1 = new Money("1500");

		Money expected = new Money("500");
		Money actual = value1.minus(value);

		assertEquals(expected, actual);
	}
}
