package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.InvalidNumberException;

@SuppressWarnings("NonAsciiCharacters")
public class NumberTest {
	@ParameterizedTest
	@ValueSource(strings = {"1", "45", "33"})
	@DisplayName("정상적으로 로또를 구성하는 수를 생성하는 경우")
	void correctNumber(String value) {
		assertThat(Number.of(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "46", "-1"})
	@DisplayName("정상적인 수의 범위를 벗어나는 경우")
	void rangeOver(String value) {
		assertThatThrownBy(() -> {
			Number.of(value);
		}).isInstanceOf(InvalidNumberException.class)
			.hasMessageMatching("로또 번호는 1에서 45까지만 가능합니다");
	}

	@ParameterizedTest
	@ValueSource(strings = {"asd", "100a", "a100", "천원"})
	@DisplayName("수를 문자열로 입력하는 경우")
	void numberFormatTest(String value) {
		assertThatThrownBy(() -> {
			Number.of(value);
		}).isInstanceOf(NumberFormatException.class)
			.hasMessageMatching("문자는 사용이 불가능합니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "   "})
	void 공백_테스트(String value) {
		assertThatThrownBy(() -> {
			Number.of(value);
		}).isInstanceOf(InvalidNumberException.class)
			.hasMessageMatching("공백은 사용할 수 없습니다.");
	}

	@Test
	void NULL_테스트() {
		assertThatThrownBy(() -> {
			Number.of(null);
		}).isInstanceOf(InvalidNumberException.class)
			.hasMessageMatching("널은 입력되지 않습니다.");
	}

	@Test
	void toStringTest() {
		assertThat(Number.of("3").toString()).isEqualTo("3");
	}
}
