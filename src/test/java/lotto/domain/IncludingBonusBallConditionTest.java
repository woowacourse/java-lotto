package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class IncludingBonusBallConditionTest {
	@DisplayName("보너스볼을 가질 수 있는 경우)")
	@ParameterizedTest
	@EnumSource(value = IncludingBonusBallCondition.class, names = {"NO_MATTER", "MUST_INCLUDE"})
	void includeTest(IncludingBonusBallCondition status) {
		assertThat(status.isAcceptableBonusCondition(true)).isTrue();
	}

	@DisplayName("보너스볼을 가질 수 없는 경우")
	@ParameterizedTest
	@EnumSource(value = IncludingBonusBallCondition.class, names = {"NO_MATTER", "MUST_NOT_INCLUDE"})
	void nonIncludeTest(IncludingBonusBallCondition status) {
		assertThat(status.isAcceptableBonusCondition(false)).isTrue();
	}
}
