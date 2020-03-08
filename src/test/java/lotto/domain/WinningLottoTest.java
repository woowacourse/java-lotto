package lotto.domain;

import lotto.exceptions.WinningLottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WinningLottoTest {
	@ParameterizedTest
	@CsvSource(value = {"1, 2, 3, 4, 5, 6:7", "1, 2, 3, 43, 44, 45:4", "6, 5, 4, 3, 2, 1,:7"},
			delimiter = ':')
	void WinningLottoNumbers(String input1, int input2) {
		// when
		WinningLotto result = WinningLotto.of(input1, input2);

		// then
		Assertions.assertThat(result.getWinningLottoNumbers())
				.isEqualTo(Lotto.of(input1));
		Assertions.assertThat(result.getBonus())
				.isEqualTo(LottoNumber.of(input2));
	}


	@ParameterizedTest
	@CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,4,5,6:3", "1,2,3,4,5,6:1"}, delimiter = ':')
	void WinningLottoNumbers_WinningNumbersContainsBonusNumber_ShouldThrowException(
			String input1, int input2) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			WinningLotto.of(input1, input2);
		}).isInstanceOf(WinningLottoException.class)
				.hasMessageMatching(WinningLottoException.MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("generateCountMatchingLottoNumbersInput")
	void countMatchingLottoNumbers(String input, WinningType expected) {
		// given
		Lotto serialLottoNumber = Lotto.of(input);

		// when
		WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 7);
		WinningType winningType = winningLotto.findMatchingWinningTypeWith(serialLottoNumber);

		// then
		Assertions.assertThat(winningType)
				.isEqualTo(expected);
	}

	static Stream<Arguments> generateCountMatchingLottoNumbersInput() {
		return Stream.of(
				Arguments.of("1, 2, 3, 4, 5, 6", WinningType.FIRST_PLACE),
				Arguments.of("1, 2, 4, 5, 6, 7", WinningType.SECOND_PLACE),
				Arguments.of("1, 2, 4, 5, 6, 45", WinningType.THIRD_PLACE),
				Arguments.of("2, 3, 4, 5, 7, 44", WinningType.FOURTH_PLACE),
				Arguments.of("3, 5, 6, 11, 14, 23", WinningType.FIFTH_PLACE),
				Arguments.of("2, 3, 34, 45, 7, 37", WinningType.NONE),
				Arguments.of("8, 9, 10, 11, 12, 13", WinningType.NONE));
	}

}
