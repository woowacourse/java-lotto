package lotto.domain.number;

import lotto.exceptions.NotSixSizeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SerialLottoNumberTest {
	private Set<LottoNumber> intStreamToLottoNumberSet(Stream<Integer> input) {
		return input.map(LottoNumber::of)
				.collect(Collectors.toSet());
	}

	@Test
	void ofString() {
		// given
		String input = "1,2, 3, 4,5, 6";

		// when
		SerialLottoNumber result = SerialLottoNumber.of(input);

		// then
		String expected = "3,4, 5, 1, 2,6";
		Assertions.assertThat(result).isEqualTo(SerialLottoNumber.of(expected));
	}

	@Test
	void ofSet() {
		// given
		Set<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::of)
				.collect(Collectors.toSet());

		// when
		SerialLottoNumber result = SerialLottoNumber.of(lottoNumbers);

		// then
		Assertions.assertThat(result).isEqualTo(SerialLottoNumber.of(lottoNumbers));
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "1,2,3,4,5", "1,2,3,4,5,6,7", ",,,"})
	void create_NotSizeSix_ThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			SerialLottoNumber.of(input);
		}).isInstanceOf(NotSixSizeException.class)
				.hasMessageContaining("의 사이즈가 6이 아닙니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "12.3,3,4", "-1,5,aa"})
	void create_InputNotInteger_ThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			SerialLottoNumber.of(input);
		}).isInstanceOf(NumberFormatException.class);
	}

	@ParameterizedTest
	@MethodSource("generateContainInput")
	void contains(Stream<Integer> given, int input, boolean expected) {
		// given
		SerialLottoNumber lottoTicket = SerialLottoNumber.of(intStreamToLottoNumberSet(given));

		// when
		boolean result = lottoTicket.contains(LottoNumber.of(input));

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateContainInput() {
		return Stream.of(Arguments.of(Stream.of(1, 2, 3, 4, 5, 6), 4, true),
				Arguments.of(Stream.of(1, 2, 3, 4, 5, 7), 6, false));
	}

	@ParameterizedTest
	@MethodSource("generateMatchingInput")
	void countMatchingNumber(Stream<Integer> winningNumbersInput, Stream<Integer> ticketInput, int expected) {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumber.of(intStreamToLottoNumberSet(winningNumbersInput));
		SerialLottoNumber ticket = SerialLottoNumber.of(intStreamToLottoNumberSet(ticketInput));

		// when
		int result = winningNumbers.countMatchingNumber(ticket);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateMatchingInput() {
		return Stream.of(Arguments.of(Stream.of(1, 2, 3, 4, 5, 6), Stream.of(1, 2, 3, 4, 5, 6), 6),
				Arguments.of(Stream.of(1, 2, 3, 4, 5, 7), Stream.of(1, 2, 3, 4, 5, 6), 5),
				Arguments.of(Stream.of(1, 2, 3, 4, 5, 6), Stream.of(1, 7, 8, 9, 10, 11), 1),
				Arguments.of(Stream.of(1, 2, 3, 4, 5, 6), Stream.of(7, 8, 9, 10, 11, 12), 0));
	}
}