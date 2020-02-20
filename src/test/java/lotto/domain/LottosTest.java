package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottosTest {
	@ParameterizedTest
	@NullAndEmptySource
	void Lottos가_null이_아닌지_체크(List<Lotto> value) {
		assertThatThrownBy(() -> new Lottos(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
