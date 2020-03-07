package lotto.domain;

import lotto.exceptions.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTest {
	@Test
	void Lotto() {
		// given
		String input = "1, 10, 3, 11, 5, 6";

		// when
		Lotto lotto = Lotto.of(input);

		// then
		List<Integer> sortedInput = Arrays.asList(1, 3, 5, 6, 10, 11);
		List<LottoNumber> expected = sortedInput.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toList());

		Assertions.assertThat(lotto.getLottoNumbers())
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
	void Lotto_NotSizeSix_ShouldThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Lotto.of(input);
		}).isInstanceOf(LottoException.class)
				.hasMessageMatching(LottoException.MESSAGE);
	}

	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,4,5", "1,3,2,3,4,3", "1,2,3,1,2,3"})
	void SerialLottoNumber_DuplicatedNumbers_ShouldThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			Lotto.of(input);
		}).isInstanceOf(LottoException.class)
				.hasMessageMatching(LottoException.MESSAGE);
	}

	@Test
	void getLottoNumbers() {
		// given
		String input = "1, 45, 3, 4, 5, 6";

		// when
		List<LottoNumber> result = Lotto.of(input).getLottoNumbers();

		//then
		List<LottoNumber> expected = Stream.of(1, 3, 4, 5, 6, 45)
				.map(LottoNumber::of)
				.collect(Collectors.toUnmodifiableList());
		Assertions.assertThat(result).isEqualTo(expected);
	}
}
