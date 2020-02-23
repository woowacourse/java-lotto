package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class InputValidatorTest {
	@DisplayName("입력받은 문자가 숫자인지 테스트")
	@Test
	void validateInteger() {
		InputValidator.validateInteger("1");
		InputValidator.validateInteger("45");

		assertThatThrownBy(() -> InputValidator.validateInteger("a"))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
