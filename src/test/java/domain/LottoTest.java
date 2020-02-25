package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exception.LottoInputException;

class LottoTest {

	private static Stream<Arguments> createNumbers() {
		return Stream.of(
			Arguments.of(
				"1, 2, 2, 3, 4, 5"
			), Arguments.of(
				"1, 2, 3, 4, 5"
			)
		);
	}

	@ParameterizedTest
	@MethodSource("createNumbers")
	void createTest(String numbers) {
		assertThatThrownBy(() -> {
			new Lotto(numbers);
		})
			.isInstanceOf(LottoInputException.class)
			.hasMessage("로또 번호가 중복되거나, 6개를 입력하지 않았습니다.");
	}

	@Test
	void isContains() {
		// given
		Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
		LottoNumber containNumber = LottoNumber.createNumber(1);
		// then
		assertThat(lotto.isContains(containNumber)).isTrue();
	}

	@Test
	void compare() {
		// given
		Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
		Lotto winningLotto = new Lotto("1, 2, 3, 4, 5, 7");
		LottoNumber bonusNumber = LottoNumber.createNumber(6);
		// then
		assertThat(lotto.compare(winningLotto, bonusNumber)).isEqualTo(Rank.SECOND);
	}
}