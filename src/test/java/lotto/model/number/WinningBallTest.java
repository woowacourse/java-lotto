package lotto.model.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningBallTest {

	@DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다")
	@Test
	void type_exception() {
		assertThatThrownBy(() -> {
			WinningBall.from("일");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 숫자로만 입력해주세요");
	}

	@DisplayName("당첨 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
	@Test
	void bound_exception() {
		assertThatThrownBy(() -> {
			WinningBall.from("0");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("같은 숫자일 경우 equals()를 사용하면 같은 값으로 인식된다")
	@Test
	void equals_test() {
		assertThat(WinningBall.from("1")).isEqualTo(WinningBall.from("1"));
	}
}
