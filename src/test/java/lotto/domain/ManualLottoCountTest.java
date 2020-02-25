package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.model.ManualLottoCount;
import lotto.exception.InvalidRangeException;

public class ManualLottoCountTest {
	@ParameterizedTest
	@ValueSource(strings = {"-3", "-1"})
	void nagativeTest(String input) {
		assertThatThrownBy(() -> new ManualLottoCount(input)).isInstanceOf(InvalidRangeException.class)
			.hasMessageMatching("음수는 입력할 수 없습니다.");
	}
}
