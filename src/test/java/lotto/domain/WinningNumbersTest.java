package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
	@DisplayName("생성자 테스트 - 6자리를 넘는 경우")
	@Test
	void checkValidationWhenOverNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6, 7", "10")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@DisplayName("생성자 테스트 - 6자리 미만인 경우")
	@Test
	void checkValidationWhenUnderNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5", "11")
		).withMessage("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
	}

	@DisplayName("생성자 테스트 - 문자가 입력 된 경우")
	@Test
	void checkValidationWhenNotNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("w, r, o, o, n, g", "1")
		).withMessage("당첨 번호는 정수만 가능합니다");
	}

	@DisplayName("생성자 테스트 - 중복이 있는 경우")
	@Test
	void checkValidationWhenDuplicateNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 1, 2, 3, 4, 5", "13")
		).withMessage("중복된 번호는 허용하지 않습니다");
	}

	@DisplayName("생성자 테스트 - 0이하의 숫자가 존재 하는 경우")
	@Test
	void checkValidationWhenEachUnderRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("0, 1, 23, 34, 22, 3", "4")
		).withMessage("로또 숫자는 0이하 일 수 없습니다.");
	}

	@DisplayName("생성자 테스트 - 45를 초과하는 숫자가 존재 하는 경우")
	@Test
	void checkValidationWhenEachOverRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("46, 47123, 48234345, 78123123, 893, 90134234", "23")
		).withMessage("로또 숫자는 45를 넘기면 안됩니다.");
	}

	@DisplayName("생성자 테스트 - 보너스 번호에 문자가 입력 된 경우")
	@Test
	void checkValidationWhenBonusNumberNotInteger() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6", "a")
		).withMessage("보너스 번호는 정수만 가능합니다");
	}

	@DisplayName("생성자 테스트 - 당첨 번호와 보너스번호가 중복되는 경우")
	@Test
	void checkValidationWhenBonusNumberDuplicateLottoNumbers() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6","2")
		).withMessage("보너스 번호 일반 당첨번호와 중복될수 없습니다.");
	}

	@DisplayName("보너스 번호 매치 여부, false 일때")
	@Test
	void checkIsFalseWhenNotMatchWithBonusNumber() {
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		LottoNumber lottoNumber = new LottoNumber(2);

		assertThat(winningNumbers.isMatchWithBonus(lottoNumber)).isFalse();
	}

	@DisplayName("보너스 번호 매치 여부, true 일때")
	@Test
	void checkIsTrueWhenMatchWithBonusNumber() {
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		LottoNumber lottoNumber = new LottoNumber(7);

		assertThat(winningNumbers.isMatchWithBonus(lottoNumber)).isTrue();
	}
}
