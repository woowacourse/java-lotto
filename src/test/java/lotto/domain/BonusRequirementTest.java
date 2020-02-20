package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BonusRequirementTest {
	@ParameterizedTest
	@MethodSource("generateIsSatisfiedByInput")
	void isSatisfiedBy(BonusRequirement given, boolean input, boolean expected) {
		// when
		boolean result = given.isSatisfiedBy(input);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateIsSatisfiedByInput() {
		return Stream.of(Arguments.of(BonusRequirement.NO_MATTER, false, true),
				Arguments.of(BonusRequirement.NO_MATTER, true, true),
				Arguments.of(BonusRequirement.TRUE, false, false),
				Arguments.of(BonusRequirement.TRUE, true, true),
				Arguments.of(BonusRequirement.FALSE, false, true),
				Arguments.of(BonusRequirement.FALSE, true, false));
	}
}
