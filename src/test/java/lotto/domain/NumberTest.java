package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.InvalidNumberException;

public class NumberTest {
	@DisplayName("Number 생성자에 1이상 45이하의 정수 입력이 들어올 때 객체 생성")
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void constructor_WithinBoundNumber_CreatedNumber(int number) {
		assertThat(Number.valueOf(number))
			.isInstanceOf(Number.class);
	}

	@DisplayName("Number 생성자에 0이하 46이상의 정수 입력이 들어올 때 InvalidNumberException 발생")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void constructor_OutOfBoundNumber_ExceptionThrown(int number) {
		assertThatThrownBy(() -> Number.valueOf(number))
			.isInstanceOf(InvalidNumberException.class);
	}

	@DisplayName("valueOf 메소드에 1이상 45이하의 정수 입력이 들어올 때 캐싱된 Number 객체를 반환")
	@Test
	void valueOf_Number_GetNumber() {
		assertThat(Number.valueOf(3) == Number.valueOf(3)).isTrue();
		assertThat(Number.valueOf(3)).extracting("number").isEqualTo(3);
	}
}
