package lotto.domain;

import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningInformationTest {
	@Test
	void WinningLottoNumbers() {
		// given
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber winningLottoNumbers = new SerialLottoNumber(lottoNumbers);
		LottoNumber bonus = LottoNumber.getLottoNumber(7);

		// when
		WinningInformation result = new WinningInformation(winningLottoNumbers, bonus);

		// then
		Assertions.assertThat(result.getWinningLottoNumbers())
				.isEqualTo(winningLottoNumbers);
		Assertions.assertThat(result.getBonusLottoNumber())
				.isEqualTo(bonus);
	}


	@Test
	void WinningLottoNumbers_WinningNumbersContainsBonusNumber_ShouldThrowException() {
		// given
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber winningLottoNumbers = new SerialLottoNumber(lottoNumbers);
		LottoNumber bonus = LottoNumber.getLottoNumber(6);

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			new WinningInformation(winningLottoNumbers, bonus);
		}).isInstanceOf(WinningLottoNumbersIllegalArgumentException.class)
				.hasMessageMatching("보너스 번호가 당첨 번호에 포함되지 않아야 합니다.");
	}

	@ParameterizedTest
	@MethodSource("generateCountMatchingLottoNumbersInput")
	void countMatchingLottoNumbers(List<Integer> input, WinningType expected) {
		// given
		List<LottoNumber> winning = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber winningNumbers = new SerialLottoNumber(winning);
		LottoNumber bonus = LottoNumber.getLottoNumber(7);
		WinningInformation winningInformation = new WinningInformation(winningNumbers, bonus);

		List<LottoNumber> lottoNumbers = input.stream()
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber lottoTicket = new SerialLottoNumber(lottoNumbers);

		// when
		WinningType winningType = winningInformation.findMatchingWinningTypeWith(lottoTicket);

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
;