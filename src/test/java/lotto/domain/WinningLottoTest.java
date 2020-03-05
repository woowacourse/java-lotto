package lotto.domain;

import lotto.exceptions.WinningLottoIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WinningLottoTest {
	@Test
	void WinningLottoNumbers() {
		// when
		WinningLotto result = new WinningLotto(Lotto.of("1, 2, 3, 4, 5, 6"), LottoNumber.of(7));

		// then
		Assertions.assertThat(result.getWinningLottoNumbers())
				.isEqualTo(Lotto.of("1, 2, 3, 4, 5, 6"));
		Assertions.assertThat(result.getBonus())
				.isEqualTo(LottoNumber.of(7));
	}


	@Test
	void WinningLottoNumbers_WinningNumbersContainsBonusNumber_ShouldThrowException() {
		// given
		List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Lotto winningLottoNumbers = Lotto.of(lottoNumbers);
		LottoNumber bonus = LottoNumber.of(6);

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			new WinningLotto(winningLottoNumbers, bonus);
		}).isInstanceOf(WinningLottoIllegalArgumentException.class)
				.hasMessageMatching(WinningLottoIllegalArgumentException.MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("generateCountMatchingLottoNumbersInput")
	void countMatchingLottoNumbers(List<Integer> input, WinningType expected) {
		// given
		List<Integer> winning = Arrays.asList(1, 2, 3, 4, 5, 6);
		Lotto winningNumbers = Lotto.of(winning);
		LottoNumber bonus = LottoNumber.of(7);
		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

		Lotto serialLottoNumber = Lotto.of(input);

		// when
		WinningType winningType = winningLotto.findMatchingWinningTypeWith(serialLottoNumber);

		// then
		Assertions.assertThat(winningType)
				.isEqualTo(expected);
	}

	static Stream<Arguments> generateCountMatchingLottoNumbersInput() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), WinningType.FIRST_PLACE),
				Arguments.of(Arrays.asList(1, 2, 4, 5, 6, 7), WinningType.SECOND_PLACE),
				Arguments.of(Arrays.asList(1, 2, 4, 5, 6, 45), WinningType.THIRD_PLACE),
				Arguments.of(Arrays.asList(2, 3, 4, 5, 7, 44), WinningType.FOURTH_PLACE),
				Arguments.of(Arrays.asList(3, 5, 6, 11, 14, 23), WinningType.FIFTH_PLACE),
				Arguments.of(Arrays.asList(2, 3, 34, 45, 7, 37), WinningType.NONE),
				Arguments.of(Arrays.asList(8, 9, 10, 11, 12, 13), WinningType.NONE));
	}
}
