package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BonusBallMatchStatusTest {
	@DisplayName("보너스볼을 가질 수 있는 경우)")
	@ParameterizedTest
	@EnumSource(value = BonusBallMatchStatus.class, names = {"INCLUDING_OR_NOT", "INCLUDING"})
	void includeTest(BonusBallMatchStatus status) {
		assertThat(status.contains(true)).isTrue();
	}

	@DisplayName("보너스볼을 가질 수 없는 경우")
	@ParameterizedTest
	@EnumSource(value = BonusBallMatchStatus.class, names = {"INCLUDING_OR_NOT", "NOT_INCLUDING"})
	void nonIncludeTest(BonusBallMatchStatus status) {
		assertThat(status.contains(false)).isTrue();
	}
}
