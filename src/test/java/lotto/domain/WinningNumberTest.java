package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

	static Stream<Arguments> generateInput_중복번호() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "3", "4", "5")),
			Arguments.of(Arrays.asList("1", "4", "7", "13", "13", "21")));
	}

	@ParameterizedTest
	@MethodSource("generateInput_중복번호")
	void 중복_번호기_있는_경우(List<String> value) {
		assertThatThrownBy(() -> new WinningNumber(value, 34))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	static Stream<Arguments> generateInput_번호범위() {
		return Stream.of(Arguments.of(Arrays.asList("-9999", "2", "1", "3", "4", "5")),
			Arguments.of(Arrays.asList("1", "4", "7", "5", "13", "0")),
			Arguments.of(Arrays.asList("1", "4", "7", "46", "13", "21")),
			Arguments.of(Arrays.asList("1", "4", "7", "13", "19999", "21")));
	}

	@ParameterizedTest
	@MethodSource("generateInput_번호범위")
	void 번호_범위를_벗어나는_경우(List<String> value) {
		assertThatThrownBy(() -> new WinningNumber(value, 34))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("범위");
	}

	static Stream<Arguments> generateInput_중복보너스번호() {
		return Stream.of(Arguments.of(Arrays.asList("45", "2", "1", "3", "4", "5"), 3),
			Arguments.of(Arrays.asList("1", "4", "7", "5", "13", "23"), 23));
	}

	@ParameterizedTest
	@MethodSource("generateInput_중복보너스번호")
	void 중복_보너스_번호인_경우(List<String> value, int bonus) {
		assertThatThrownBy(() -> new WinningNumber(value, bonus))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복된 보너스");
	}

	@ParameterizedTest
	@ValueSource(ints = {-1000, 0, 46, 10000})
	void 보너스_번호_범위를_벗어나는_경우(int bonus) {
		assertThatThrownBy(() -> new WinningNumber(Arrays.asList("1", "2", "3", "4", "5", "6"), bonus))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("보너스 번호가 범위");
	}
}
