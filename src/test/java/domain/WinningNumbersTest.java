package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

	@Test
	@DisplayName("숫자가 아닌 경우")
	void checkNotDigit() {
		//given
		String[] input = new String[] {"1", "a", "11", "b", "45", "c"};

		//then
		assertThatThrownBy(() -> WinningNumbers.from(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("당첨 번호는 숫자여야 합니다");
	}

	@Test
	@DisplayName("당첨 번호의 범위를 벗어난 경우")
	void checkWinningNumbersRange() {
		//given
		String[] input = new String[] {"1", "7", "11", "30", "45", "80"};

		//then
		assertThatThrownBy(() -> WinningNumbers.from(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("당첨 번호는 1 ~ 45의 숫자여야 합니다");
	}

	@Test
	@DisplayName("입력한 숫자가 6개가 아닌 경우")
	void checkWinningNumbersSize() {
		//given
		String[] input = new String[] {"1", "7", "11", "30"};

		//then
		assertThatThrownBy(() -> WinningNumbers.from(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("당첨 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("중복된 숫자를 입력한 경우")
	void checkDuplicateWinningNumbers() {
		//given
		String[] input = new String[] {"1", "7", "11", "30", "30", "34"};

		//then
		assertThatThrownBy(() -> WinningNumbers.from(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 숫자를 입력할 수 없습니다");
	}
}