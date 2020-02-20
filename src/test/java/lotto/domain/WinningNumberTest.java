package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class WinningNumberTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 널이나_빈_값이_들어올_경우(List<String> value) {
		assertThatThrownBy(() -> new WinningNumber(value, 5))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("널이나 빈 값");
	}
}
