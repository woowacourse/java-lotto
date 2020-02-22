package lotto.domain.BonusRequirement;

import lotto.domain.BonusRequirement.BonusRequirementExpressions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BonusRequirementExpressionsTest {

	@ParameterizedTest
	@CsvSource(value = {"true,true", "false,true"})
	void isSatisfiedWhenNoMatter(boolean input, boolean expected) {
		// when
		boolean result = BonusRequirementExpressions.isSatisfiedWhenNoMatter(input);

		// then
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"true,true", "false,false"})
	void isSatisfiedWhenTrue(boolean input, boolean expected) {
		// when
		boolean result = BonusRequirementExpressions.isSatisfiedWhenTrue(input);

		// then
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"true,false", "false,true"})
	void isSatisfiedWhenFalse(boolean input, boolean expected) {
		// when
		boolean result = BonusRequirementExpressions.isSatisfiedWhenFalse(input);

		// then
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}