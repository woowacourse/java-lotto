package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BonusBallStatusTest {

	@DisplayName("보너스볼을 가질 수 있는 경우)")
	@ParameterizedTest
	@EnumSource(value = BonusBallStatus.class, names = {"INCLUDING_OR_NOT", "INCLUDING"})
	void includeTest(BonusBallStatus status) {
		assertThat(status.contains(true)).isTrue();
	}

	@DisplayName("보너스볼을 가질 수 없는 경우")
	@ParameterizedTest
	@EnumSource(value = BonusBallStatus.class, names = {"INCLUDING_OR_NOT", "NOT_INCLUDING"})
	void nonIncludeTest(BonusBallStatus status) {
		assertThat(status.contains(false)).isTrue();
	}
}
