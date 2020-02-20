package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class WinningNumberTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 널이나_빈_값이_들어올_경우(List<String> value) {
		assertThatThrownBy(() -> new WinningNumber(value, 5))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("널이나 빈 값");
	}

	static Stream<Arguments> generateInput_번호갯수() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "4", "5")),
			Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
	}

	@ParameterizedTest
	@MethodSource("generateInput_번호갯수")
	void 번호_갯수가_틀린_경우(List<String> value) {
		assertThatThrownBy(() -> new WinningNumber(value, 21))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("개여야 합니다");
	}
}
