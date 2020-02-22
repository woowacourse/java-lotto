package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BonusTypeTest {
	@ParameterizedTest
	@MethodSource("generateIsSatisfiedByInput")
	void isSatisfiedBy(BonusType given, boolean input, boolean expected) {
		// when
		boolean result = given.isMatching(input);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateIsSatisfiedByInput() {
		return Stream.of(Arguments.of(BonusType.NO_MATTER, false, true),
				Arguments.of(BonusType.NO_MATTER, true, true),
				Arguments.of(BonusType.TRUE, false, false),
				Arguments.of(BonusType.TRUE, true, true),
				Arguments.of(BonusType.FALSE, false, true),
				Arguments.of(BonusType.FALSE, true, false));
	}
}
