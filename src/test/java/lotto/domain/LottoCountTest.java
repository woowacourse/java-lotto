package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.InvalidRangeException;

public class LottoCountTest {
	@ParameterizedTest
	@ValueSource(strings = {"-3", "-1"})
	void nagativeTest(String input) {
		assertThatThrownBy(() -> new LottoCount(input,10)).isInstanceOf(InvalidRangeException.class)
			.hasMessageMatching("음수는 사용할 수 없습니다.");
	}
}
