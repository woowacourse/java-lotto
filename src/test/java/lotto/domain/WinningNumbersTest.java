package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
	@DisplayName("생성자 테스트 - 당첨 번호와 보너스번호가 중복되는 경우")
	@Test
	void checkValidationWhenBonusNumberDuplicateLottoNumbers() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new WinningNumbers("1, 2, 3, 4, 5, 6","2")
		).withMessage("보너스 번호는 일반 당첨번호와 중복될수 없습니다.");
	}

	@DisplayName("보너스 번호 매치 여부, false 일때")
	@Test
	void checkIsFalseWhenNotMatchWithBonusNumber() {
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		LottoNumber lottoNumber = new LottoNumber("2");

		assertThat(winningNumbers.isMatchWithBonus(lottoNumber)).isFalse();
	}

	@DisplayName("보너스 번호 매치 여부, true 일때")
	@Test
	void checkIsTrueWhenMatchWithBonusNumber() {
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		LottoNumber lottoNumber = new LottoNumber("7");

		assertThat(winningNumbers.isMatchWithBonus(lottoNumber)).isTrue();
	}
}
