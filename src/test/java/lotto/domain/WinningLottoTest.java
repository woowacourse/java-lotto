package lotto.domain;

import lotto.exceptions.WinningLottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WinningLottoTest {
	@Test
	void WinningLottoNumbers() {
		// when
		WinningLotto result = WinningLotto.of("1, 2, 3, 4, 5, 6", 7);

		// then
		Assertions.assertThat(result.getWinningLottoNumbers())
				.isEqualTo(Lotto.of("1, 2, 3, 4, 5, 6"));
		Assertions.assertThat(result.getBonus())
				.isEqualTo(LottoNumber.of(7));
	}


	@Test
	void WinningLottoNumbers_WinningNumbersContainsBonusNumber_ShouldThrowException() {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			WinningLotto.of("1,2,3,4,5,6", 6);
		}).isInstanceOf(WinningLottoException.class)
				.hasMessageMatching(WinningLottoException.MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("generateCountMatchingLottoNumbersInput")
	void countMatchingLottoNumbers(int[] input, WinningType expected) {
		// given
		WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 7);

		Lotto serialLottoNumber = Lotto.of(input);

		// when
		WinningType winningType = winningLotto.findMatchingWinningTypeWith(serialLottoNumber);

		// then
		Assertions.assertThat(winningType)
				.isEqualTo(expected);
	}

	static Stream<Arguments> generateCountMatchingLottoNumbersInput() {
		return Stream.of(Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, WinningType.FIRST_PLACE),
				Arguments.of(new int[]{1, 2, 4, 5, 6, 7}, WinningType.SECOND_PLACE),
				Arguments.of(new int[]{1, 2, 4, 5, 6, 45}, WinningType.THIRD_PLACE),
				Arguments.of(new int[]{2, 3, 4, 5, 7, 44}, WinningType.FOURTH_PLACE),
				Arguments.of(new int[]{3, 5, 6, 11, 14, 23}, WinningType.FIFTH_PLACE),
				Arguments.of(new int[]{2, 3, 34, 45, 7, 37}, WinningType.NONE),
				Arguments.of(new int[]{8, 9, 10, 11, 12, 13}, WinningType.NONE));
	}

}
