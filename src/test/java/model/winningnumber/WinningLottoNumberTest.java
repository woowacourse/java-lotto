package model.winningnumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.strategy.TestLottoNumberGenerationStrategy;
import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;

public class WinningLottoNumberTest {
	@Test
	@DisplayName("당첨 번호가 범위 밖인 경우")
	void validateWinningNumberOutOfRange() {
		assertThatThrownBy(
			() -> new WinningLottoNumber(
				LottoNumbers.from(new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 0))),
				LottoNumber.valueOf(10)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또 번호는 1~45 숫자여야 합니다.");
	}

	@Test
	@DisplayName("보너스 볼이 당첨 번호와 중복되는지 검증")
	void validateReduplicationWithBonusBall() {
		assertThatThrownBy(
			() -> new WinningLottoNumber(
				LottoNumbers.from(new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))),
				LottoNumber.valueOf(6)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 당첨 번호와 보너스 볼이 중복됩니다.");
	}
}
