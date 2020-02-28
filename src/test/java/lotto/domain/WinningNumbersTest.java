package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
	@Test
	@DisplayName("당첨번호가 6자리 초과일경우 익셉션을 발생한다")
	void checkValidationWhenOverNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6, 7", "10")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@Test
	@DisplayName("당첨 번호가 6자리 미만 일경우 익셉션을 발생한다")
	void checkValidationWhenUnderNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5", "11")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@Test
	@DisplayName("당첨번호가 정수가 아닐경우 익셉션을 발생한다")
	void checkValidationWhenNotNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("w, r, o, o, n, g", "1")
		).withMessage("당첨 번호는 정수만 가능합니다");
	}

	@Test
	@DisplayName("당첨 번호가 중복일 경우 익셉션을 발생한다")
	void checkValidationWhenDuplicateNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 1, 2, 3, 4, 5", "13")
		).withMessage("중복된 번호는 허용하지 않습니다");
	}

	@Test
	@DisplayName("당첨번호가 0이하의 값이 있을 경우 익셉션을 발생한다")
	void checkValidationWhenEachUnderRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("0, 1, 23, 34, 22, 3", "4")
		).withMessage("로또 숫자는 0이하 일 수 없습니다.");
	}

	@Test
	@DisplayName("당첨 번호가 45를 초과하면 익셉션을 발생한다")
	void checkValidationWhenEachOverRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("46, 47123, 48234345, 78123123, 893, 90134234", "23")
		).withMessage("로또 숫자는 45를 넘기면 안됩니다.");
	}

	@Test
	@DisplayName("보너스 번호가 정수가 아닐 경우 익셉션을 발생한다")
	void checkValidationWhenBonusNumberNotInteger() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6", "a")
		).withMessage("보너스 번호는 정수만 가능합니다");
	}

	@Test
	@DisplayName("보너스 번호가 당첨번호와 중복되면 익셉션이 발생한다")
	void checkValidationWhenBonusNumberDuplicateLottoNumbers() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6","2")
		).withMessage("보너스 번호 일반 당첨번호와 중복될수 없습니다.");
	}
}
