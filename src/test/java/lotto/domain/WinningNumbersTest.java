package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class WinningNumbersTest {
	@Test
	void checkValidationWhenOverNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6, 7")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@Test
	void checkValidationWhenUnderNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@Test
	void checkValidationWhenNotNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("w, r, o, o, n, g")
		).withMessage("당첨 번호는 정수만 가능합니다");
	}

	@Test
	void checkValidationWhenDuplicateNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 1, 2, 3, 4, 5")
		).withMessage("중복된 번호는 허용하지 않습니다");
	}


}
