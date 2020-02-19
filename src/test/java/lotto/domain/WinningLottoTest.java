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
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThatThrownBy(()->{
			new WinningLotto(new Lotto(winningNumbers), Number.of("3"));
		}).isInstanceOf(InvalidWinningLottoException.class)
			.hasMessageMatching("보너스 번호와 당첨번호는 중복될 수 없습니다.");
	}

	@Test
	void name() {
		List<Number> winningNumbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThat(winningNumbers.contains(Number.of("3")));
	}
}