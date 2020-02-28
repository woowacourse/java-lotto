package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class InputValidatorTest {
	@DisplayName("입력받은 문자가 숫자인지 테스트")
	@Test
	void validateInteger() {
		InputValidator.validateNumber("1");
		InputValidator.validateNumber("45");

		assertThatThrownBy(() -> InputValidator.validateNumber("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 아닌 문자를 입력하였습니다.");
	}
}
