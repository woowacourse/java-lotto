package lotto.domain;

import lotto.exceptions.BallNumberOutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 61, -1})
	void Number_LessThanOneOrMoreThanSixty_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			BallNumber ballNumber = new BallNumber(input);
		}).isInstanceOf(BallNumberOutOfRangeException.class)
				.hasMessageMatching("-?[0-9]+" + BallNumberOutOfRangeException.MESSAGE );
	}
}