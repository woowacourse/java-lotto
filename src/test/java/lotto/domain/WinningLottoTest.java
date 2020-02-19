package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidWinningLottoException;

class WinningLottoTest {
	@Test
	@DisplayName("보너스번호와 당첨번호가 중복되는 경우 예외 발생")
	void bonusNumberDuplication() {
		List<Number> winningNumbers = Arrays.asList(
			new Number("1"),
			new Number("2"),
			new Number("3"),
			new Number("4"),
			new Number("5"),
			new Number("6")
		);
		assertThatThrownBy(()->{
			new WinningLotto(new WinningNumbers(winningNumbers), new BonusNumber("3"));
		}).isInstanceOf(InvalidWinningLottoException.class)
			.hasMessageMatching("보너스 번호와 당첨번호는 중복될 수 없습니다.");
	}
}