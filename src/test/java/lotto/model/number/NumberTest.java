package lotto.model.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

	@DisplayName("로또 번호가 숫자가 아니면 예외가 발생한다")
	@Test
	void type_exception() {
		assertThatThrownBy(() -> {
			Number.from("일");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 숫자로만 입력해주세요");
	}

	@DisplayName("로또 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
	@Test
	void bound_exception() {
		assertThatThrownBy(() -> {
			Number.from("100");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}
}
