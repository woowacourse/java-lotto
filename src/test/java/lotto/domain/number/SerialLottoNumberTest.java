package lotto.domain.number;

import lotto.exceptions.NotSixSizeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SerialLottoNumberTest {
	private Set<LottoNumber> intStreamToLottoNumberSet(Stream<Integer> input) {
		return input.map(LottoNumber::of)
				.collect(Collectors.toSet());
	}

	@Test
	void SerialLottoNumbers() {
		// given
		Stream<Integer> input = Stream.of(2, 1, 4, 5, 3, 6);
		Set<LottoNumber> given = intStreamToLottoNumberSet(input);

		// when
		SerialLottoNumber result = new SerialLottoNumber(given);

		// then
		Set<LottoNumber> expected = intStreamToLottoNumberSet(Stream.of(1, 2, 3, 4, 5, 6));
		Assertions.assertThat(result).isEqualTo(new SerialLottoNumber(expected));
	}

	@ParameterizedTest
	@MethodSource("generateNotSixSizeInput")
	void SerialLottoNumbers_NotSizeSize_ThrowException(Stream<Integer> input) {
		// given
		Set<LottoNumber> given = intStreamToLottoNumberSet(input);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			new SerialLottoNumber(given);
		}).isInstanceOf(NotSixSizeException.class)
				.hasMessageContaining("의 사이즈가 6이 아닙니다.");
	}

	static Stream<Arguments> generateNotSixSizeInput() {
		return Stream.of(Arguments.of(Stream.of()),
				Arguments.of(Stream.of(1, 2, 3, 4, 5)),
				Arguments.of(Stream.of(1, 2, 3, 4, 5, 6, 7)));
	}

	@ParameterizedTest
	@MethodSource("generateContainInput")
	void contains(Stream<Integer> given, int input, boolean expected) {
		// given
		SerialLottoNumber lottoTicket = new SerialLottoNumber(intStreamToLottoNumberSet(given));

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
		SerialLottoNumber winningNumbers = new SerialLottoNumber(intStreamToLottoNumberSet(winningNumbersInput));
		SerialLottoNumber ticket = new SerialLottoNumber(intStreamToLottoNumberSet(ticketInput));

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