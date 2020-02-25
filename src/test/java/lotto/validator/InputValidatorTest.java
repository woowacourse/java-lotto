package lotto.validator;

import lotto.domain.Money;
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

	@DisplayName("수동으로 구입가능한 로또 수를 초과하여 구입하려 할 때 예외처리")
	@Test
	void validateManualCount() {
		InputValidator.validateManualCount(2, new Money("2000"));

		assertThatThrownBy(() -> InputValidator
				.validateManualCount(3, new Money("2000")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("구입 가능한 수보다 큰 수를 입력하였습니다.");
	}
}
