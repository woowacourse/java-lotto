package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
	static Stream<Arguments> generateInput_numberAmount() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "4", "5")),
				Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
	}

	static Stream<Arguments> generateInput_duplicateNumber() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "3", "4", "5")),
				Arguments.of(Arrays.asList("1", "4", "7", "13", "13", "21")));
	}

	static Stream<Arguments> generateInput_numberRange() {
		return Stream.of(Arguments.of(Arrays.asList("-9999", "2", "1", "3", "4", "5")),
				Arguments.of(Arrays.asList("1", "4", "7", "5", "13", "0")),
				Arguments.of(Arrays.asList("1", "4", "7", "46", "13", "21")),
				Arguments.of(Arrays.asList("1", "4", "7", "13", "19999", "21")));
	}

	static Stream<Arguments> generateInput_duplicateBonusNumber() {
		return Stream.of(Arguments.of(Arrays.asList("45", "2", "1", "3", "4", "5"), 3),
				Arguments.of(Arrays.asList("1", "4", "7", "5", "13", "23"), 23));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("널이나 빈 값이 들어올 경우")
	void checkIfNullOrEmpty(List<String> value) {
		assertThatThrownBy(() -> new WinningLotto(value, 5))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("널이나 빈 값");
	}

	@ParameterizedTest
	@MethodSource("generateInput_numberAmount")
	@DisplayName("번호 갯수가 틀린 경우")
	void checkNumberAmountMismatch(List<String> value) {
		assertThatThrownBy(() -> new WinningLotto(value, 21))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("개여야 합니다");
	}

	@ParameterizedTest
	@MethodSource("generateInput_duplicateNumber")
	@DisplayName("중복 번호가 있는 경우")
	void checkDuplicateNumber(List<String> value) {
		assertThatThrownBy(() -> new WinningLotto(value, 34))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("중복");
	}

	@ParameterizedTest
	@MethodSource("generateInput_numberRange")
	@DisplayName("번호 범위를 벗어나는 경우")
	void checkNumberRange(List<String> value) {
		assertThatThrownBy(() -> new WinningLotto(value, 34))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("범위");
	}

	@ParameterizedTest
	@MethodSource("generateInput_duplicateBonusNumber")
	@DisplayName("보너스 번호가 중복되는 경우")
	void checkDuplicateBonusNumber(List<String> value, int bonus) {
		assertThatThrownBy(() -> new WinningLotto(value, bonus))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("중복된 보너스");
	}

	@ParameterizedTest
	@ValueSource(ints = {-1000, 0, 46, 10000})
	@DisplayName("보너스 번호 범위를 벗어나는 경우")
	void checkBonusNumberIsOutOfRange(int bonus) {
		assertThatThrownBy(() -> new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "6"), bonus))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("보너스 번호가 범위");
	}
}
