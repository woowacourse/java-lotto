package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
	@Test
	void initTest() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("generateLottoNumber")
	void wrongInitTest(List<Integer> numbers) {
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	static Stream<Arguments> generateLottoNumber() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 2, 3, 4, 5)),
			Arguments.of(Arrays.asList(1, 2, 2, 3, 4, 5, 6)),
			Arguments.of(Arrays.asList(1, 47, 5, 8, 3, 9)),
			Arguments.of(Arrays.asList(15, 26, 37, 18, 19, 20, 1)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5)));
	}
}