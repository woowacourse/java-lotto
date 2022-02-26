package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

	@ParameterizedTest
	@DisplayName("로또 숫자의 수가 6개가 아닌 경우 예외를 발생한다.")
	@MethodSource("generateLottoData")
	void checkLottoSize(List<Integer> generateLotto) {
		//given
		List<Number> unavailableLotto = generateLotto.stream()
			.map(Number::new)
			.collect(Collectors.toList());

		//then
		assertThatThrownBy(() -> new Lotto(unavailableLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	static Stream<Arguments> generateLottoData() {
		return Stream.of(
			Arguments.of(Collections.emptyList()),
			Arguments.of(Arrays.asList(1)),
			Arguments.of(Arrays.asList(1, 2)),
			Arguments.of(Arrays.asList(1, 2, 3)),
			Arguments.of(Arrays.asList(1, 2, 3, 4)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
		);
	}

	@Test
	@DisplayName("로또 번호는 중복된 숫자가 존재할 수 없다.")
	void checkDuplicateLottoNumber() {
		//given
		List<Number> duplicateLotto = Stream.of(1, 5, 9, 11, 11, 5)
			.map(Number::new)
			.collect(Collectors.toList());

		//then
		assertThatThrownBy(() -> new Lotto(duplicateLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 숫자를 입력할 수 없습니다");
	}
}
