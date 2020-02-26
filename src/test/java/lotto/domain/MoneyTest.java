package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
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

	@Test
	void testTicketPriceOf() {
		Money expected = new Money("1000");
		Money actual = Money.ticketPriceOf(1);
		assertEquals(expected, actual);
	}

	@Test
	void testMinus() {
		Money value = new Money("1000");
		Money value1 = new Money("1500");

		Money expected = new Money("500");
		Money actual = value1.minus(value);

		assertEquals(expected, actual);
	}
}
