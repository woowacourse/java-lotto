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
	void Lotto() {
		// when
		Lotto lotto = Lotto.of(1, 10, 3, 11, 5, 6);

		// then
		int[] sortedInput = {1, 3, 5, 6, 10, 11};
		List<LottoNumber> expected = Arrays.stream(sortedInput)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.toList());

		Assertions.assertThat(lotto.getLottoNumbers())
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateNotSizeSixInput")
	void Lotto_NotSizeSix_ShouldThrowException(int[] input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Lotto.of(input);
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
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			Lotto.of(input);
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
		Lotto expected = Lotto.of(1, 3, 4, 5, 6, 45);
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@Test
	void of_Ints() {
		// when
		Lotto result = Lotto.of(1, 45, 3, 4, 5, 6);

		//then
		Lotto expected = Lotto.of(1, 3, 4, 5, 6, 45);
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@Test
	void isInvalidInstance_ShouldReturnTrue() {
		// given
		Lotto given = Lotto.invalidInstance();

		// when
		boolean result = given.isInvalidInstance();

		// then
		Assertions.assertThat(result)
				.isTrue();
	}

	@Test
	void isInvalidInstance_ShouldReturnFalse() {
		// given
		Lotto given = Lotto.of(1, 2, 3, 4, 5, 6);

		// when
		boolean result = given.isInvalidInstance();

		// then
		Assertions.assertThat(result)
				.isFalse();
	}
}
