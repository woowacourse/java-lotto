package lotto.domain;

import lotto.exceptions.LottoIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTest {
	@Test
	void SerialLottoNumber() {
		// given
		List<Integer> input = Arrays.asList(1, 10, 3, 11, 5, 6);
		Lotto serialLottoNumber = Lotto.of(input);

		// then
		int[] sortedInput = {1, 3, 5, 6, 10, 11};
		List<LottoNumber> expected = Arrays.stream(sortedInput)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.toList());

		Assertions.assertThat(serialLottoNumber.getLottoNumbers())
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateNotSizeSixInput")
	void SerialLottoNumber_NotSizeSix_ShouldThrowException(int[] input) {
		// given
		List<Integer> lottoNumbers = Arrays.stream(input)
				.boxed()
				.collect(Collectors.toUnmodifiableList());

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Lotto.of(lottoNumbers);
		}).isInstanceOf(LottoIllegalArgumentException.class)
				.hasMessageMatching(LottoIllegalArgumentException.MESSAGE);
	}

	static Stream<Arguments> generateNotSizeSixInput() {
		return Stream.of(Arguments.of(new int[]{1, 2, 3, 4, 5}),
				Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	@ParameterizedTest
	@MethodSource("generateDuplicatedInput")
	void SerialLottoNumber_DuplicatedNumbers_ShouldThrowException(int[] input) {
		// given
		List<Integer> lottoNumbers = Arrays.stream(input)
				.boxed()
				.collect(Collectors.toUnmodifiableList());

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			Lotto.of(lottoNumbers);
		}).isInstanceOf(LottoIllegalArgumentException.class)
				.hasMessageMatching(LottoIllegalArgumentException.MESSAGE);
	}

	static Stream<Arguments> generateDuplicatedInput() {
		return Stream.of(Arguments.of(new int[]{1, 2, 3, 4, 4, 5}),
				Arguments.of(new int[]{1, 3, 2, 3, 4, 3}),
				Arguments.of(new int[]{1, 2, 3, 1, 2, 3}));
	}

	@Test
	void of_String() {
		// given
		String input = "1, 45, 3, 4, 5, 6";

		// when
		Lotto result = Lotto.of(input);

		//then
		Lotto expected = Lotto.of(Arrays.asList(1, 3, 4, 5, 6, 45));
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@Test
	void of_Integers() {
		// given
		List<Integer> input = Arrays.asList(1, 45, 3, 4, 5, 6);

		// when
		Lotto result = Lotto.of(input);

		//then
		Lotto expected = Lotto.of(Arrays.asList(1, 3, 4, 5, 6, 45));
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}
