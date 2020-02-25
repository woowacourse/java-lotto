package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {
	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Lottos가 null이 아닌지 체크")
	void checkIfLottosIsNotNull(List<Lotto> value) {
		assertThatThrownBy(() -> new Lottos(value))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("null이나 빈 값");
	}
}
