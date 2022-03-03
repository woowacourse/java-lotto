package model.winningnumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWinningNumberTest {

	@Test
	@DisplayName("당첨 번호가 범위 밖인 경우")
	void validateWinningNumberOutOfRange() {
		assertThatThrownBy(() -> new LottoWinningNumber(Arrays.asList(0, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 당첨 번호는 1~45 숫자여야 합니다.");
	}

	@Test
	@DisplayName("당첨 번호 숫자 사이즈가 6개가 아닌 경우")
	void validateInputLottoWinningNumberSize() {
		assertThatThrownBy(() -> new LottoWinningNumber(Arrays.asList(1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 당첨 번호는 6개의 숫자여야 합니다.");
	}

	@Test
	@DisplayName("당첨 번호에 중복이 있는지 검증")
	void validateWinningNumberReduplication() {
		assertThatThrownBy(() -> new LottoWinningNumber(Arrays.asList(1, 2, 3, 4, 5, 1)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 당첨 번호는 중복이 있으면 안됩니다");
	}

	@Test
	@DisplayName("보너스 볼이 당첨 번호와 중복되는지 검증")
	void validateReduplicationWithBonusBall() {
		LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThatThrownBy(() -> lottoWinningNumber.validateReduplicationWithBonusBall("1"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 당첨 번호와 보너스 볼이 중복됩니다.");
	}
}
