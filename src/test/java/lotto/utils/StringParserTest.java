package lotto.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StringParserTest {
	@ParameterizedTest
	@MethodSource("generateInput")
	void StringToIntegerList(String input, List<Integer> expected) {
		// when
		List<Integer> result = StringParser.stringToIntegerList(input);

		// then
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	static Stream<Arguments> generateInput() {
		return Stream.of(Arguments.of("1, 2,3, 4, 5,6", Arrays.asList(1, 2, 3, 4, 5, 6)),
				Arguments.of("1", Collections.singletonList(1)));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "1, 2, a, 3 ,4"})
	void StringToIntegerList_InvalidNumberFormat_ShouldThrowException(String input) {
		Assertions.assertThatThrownBy(() -> {
			// when
			List<Integer> result = StringParser.stringToIntegerList(input);
		}).isInstanceOf(NumberFormatException.class);
	}
}
