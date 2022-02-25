package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

	@Test
	@DisplayName("번호의 범위를 벗어났을 때 예외를 발생시킨다")
	void checkNumberRange() {
		assertThatThrownBy(() -> new Number(100))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("번호는 1 ~ 45의 숫자여야 합니다");
	}
}
