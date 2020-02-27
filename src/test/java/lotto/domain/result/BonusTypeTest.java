package lotto.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BonusTypeTest {
	@ParameterizedTest
	@MethodSource("generateBonusMatchingInput")
	void isEqualTo(BonusType bonusType, boolean given, boolean expected) {
		// when
		boolean result = bonusType.isEqualTo(given);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}


	static Stream<Arguments> generateBonusMatchingInput() {
		return Stream.of(Arguments.of(BonusType.NO_MATTER, true, true),
				Arguments.of(BonusType.NO_MATTER, false, true),
				Arguments.of(BonusType.TRUE, true, true),
				Arguments.of(BonusType.TRUE, false, false),
				Arguments.of(BonusType.FALSE, true, false),
				Arguments.of(BonusType.FALSE, false, true));
	}

}