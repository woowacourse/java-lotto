package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

	private final static Lotto lotto = new Lotto(intsToLottoNumbers(1, 2, 3, 4, 5, 6));
	;

	private static List<LottoNumber> intsToLottoNumbers(int... numbers) {
		return Arrays.stream(numbers)
			.mapToObj(LottoNumber::createNumber)
			.collect(Collectors.toList());
	}

	private static Stream<Arguments> createNumbersAndMessage() {
		return Stream.of(
			Arguments.of(
				intsToLottoNumbers(1, 2, 3, 4, 5, 5), "로또 번호가 중복됩니다."
			), Arguments.of(
				intsToLottoNumbers(1, 2, 3, 4, 5), "로또 번호 갯수가 6개가 아닙니다."
			)
		);
	}

	@ParameterizedTest
	@MethodSource("createNumbersAndMessage")
	void createTest(List<LottoNumber> numbers, String message) {
		assertThatThrownBy(() -> {
			new Lotto(numbers);
		})
			.isInstanceOf(RuntimeException.class)
			.hasMessage(message);
	}

	@Test
	void isContains() {
		// given
		LottoNumber containNumber = LottoNumber.createNumber(1);
		// then
		assertThat(lotto.isContains(containNumber)).isTrue();
	}

	@Test
	void compare() {
		// given
		Lotto winningLotto = new Lotto(intsToLottoNumbers(1, 2, 3, 4, 5, 7));
		LottoNumber bonusNumber = LottoNumber.createNumber(6);
		// then
		assertThat(lotto.compare(winningLotto, bonusNumber)).isEqualTo(Rank.SECOND);
	}
}