package lotto.validator;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
	@DisplayName("숫자 하나를 전달받았을때 1과 45사이에 있는지 테스트")
	@Test
	void validateLottoRange() {
		assertThatThrownBy(() -> Validator.validateLottoRange(0))
			.isInstanceOf(IllegalArgumentException.class);

		Validator.validateLottoRange(1);
		Validator.validateLottoRange(45);

		assertThatThrownBy(() -> Validator.validateLottoRange(46))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
