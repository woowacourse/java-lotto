package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class StringParserTest {
	@ParameterizedTest
	@MethodSource("generateInput")
	void parseIntegerList(String input, List<Integer> expected) {
		// when
		List<Integer> result = StringParser.parseIntegerList(input);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateInput() {
		return Stream.of(Arguments.of("1, 3,4, 5, 6", Arrays.asList(1, 3, 4, 5, 6)),
				Arguments.of("80, 4, 40,7, 45, 3", Arrays.asList(80, 4, 40, 7, 45, 3)));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "12.3,3,4", "-1,5,aa"})
	void parseIntegerList_NotInteger_ThrowException(String input) {
		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			StringParser.parseIntegerList(input);
		}).isInstanceOf(NumberFormatException.class);
	}
}