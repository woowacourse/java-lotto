package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class ListBuilderTest {
	@ParameterizedTest
	@MethodSource("generateInput")
	void mergeList(List<Integer> input1, List<Integer> input2, List<Integer> expected) {
		// when
		List<Integer> result = ListBuilder.merge(input1, input2);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateInput() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(3, 4, 5), Arrays.asList(1, 2, 3, 3, 4, 5)),
				Arguments.of(Collections.emptyList(), Collections.emptyList(), Collections.emptyList()),
				Arguments.of(Collections.emptyList(), Arrays.asList(3, 4, 5), Arrays.asList(3, 4, 5)));
	}
}