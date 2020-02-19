package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottoTest {
	@ParameterizedTest
	@CsvSource(value = {"1 true", "6 true", "0 false", "7 false"}, delimiter = ' ')
	void 로또_생성(int value, boolean expected) {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.contains(value)).isEqualTo(expected);
	}

	static Stream<Arguments> generateInput() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 로또가_null이나_빈값인_경우(List<Integer> value) {
		assertThatThrownBy(() -> new Lotto(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@ParameterizedTest
	@MethodSource("generateInput")
	void 로또_공_갯수가_맞지_않는_경우(List<Integer> value) {
		assertThatThrownBy(() -> new Lotto(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개여야 합니다");
	}
}
