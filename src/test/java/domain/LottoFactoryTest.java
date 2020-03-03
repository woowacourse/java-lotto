package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exception.InvalidLottoAmountException;

class LottoFactoryTest {

	private static Stream<Arguments> createSelfNumberAndMessage() {
		return Stream.of(
			Arguments.of(
				Arrays.asList("1, 2, 3, 4"), "로또 번호 갯수가 6개가 아닙니다."
			), Arguments.of(
				Arrays.asList("1, 2, 3, 4, 5, 5"), "로또 번호가 중복됩니다."
			), Arguments.of(
				Arrays.asList("1, 2, 3, 4, 5, -1"), "로또번호는 1~45의 수가 필요합니다."
			)
		);
	}

	@Test
	void createAutoLottos() {
		// given
		int amount = -1;
		// then
		assertThatThrownBy(() -> {
			LottoFactory.createAutoLottos(amount);
		}).isInstanceOf(InvalidLottoAmountException.class)
			.hasMessage("로또의 갯수는 음수가 올 수 없습니다.");
	}

	@ParameterizedTest
	@MethodSource("createSelfNumberAndMessage")
	void createSelfLottos(List<String> numbers, String message) {
		assertThatThrownBy(() -> {
			LottoFactory.createSelfLottos(numbers);
		}).isInstanceOf(Exception.class)
			.hasMessage(message);
	}
}