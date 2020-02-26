package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MatchCountTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 6})
	void valueOf_WithinRankMatchCount_ReturnInstance(int value) {
		assertThat(new MatchCount(value)).isInstanceOf(MatchCount.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 7})
	void valueOf_OutOfRankMatchCount_InvalidMatchCountExceptionThrown(int value) {
		assertThatThrownBy(() -> new MatchCount(value))
			.isInstanceOf(InvalidMatchCountException.class)
			.hasMessage(InvalidMatchCountException.OUT_OF_BOUND);
	}
}
