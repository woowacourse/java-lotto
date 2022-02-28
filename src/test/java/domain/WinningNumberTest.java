package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {

	@DisplayName("생성자 테스트")
	@Test
	void constructor() {
		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 7;

		assertDoesNotThrow(() -> new WinningNumber(winningNumbers, bonusBall));
	}

	@DisplayName("볼들이 6자리가 아니면 예외 테스트")
	@Test
	void sizeTest() {
		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 8);
		int bonusBall = 7;

		assertThatThrownBy(() -> new WinningNumber(winningNumbers, bonusBall))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("당첨 번호, 보너스볼 중복이면 예외 데스트")
	@Test
	void duplicate() {
		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 6;

		assertThatThrownBy(() -> new WinningNumber(winningNumbers, bonusBall))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("당첨 번호 중복이면 예외 테스트")
	@Test
	void duplicate_WinningNumber() {
		List<Integer> winningNumbers = Arrays.asList(1, 2, 2, 4, 5, 6);
		int bonusBall = 6;

		assertThatThrownBy(() -> new WinningNumber(winningNumbers, bonusBall))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
