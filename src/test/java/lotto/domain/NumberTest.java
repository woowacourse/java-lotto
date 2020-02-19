package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {
	@DisplayName("Number 생성자에 1이상 45이하의 정수 입력이 들어올 때 객체 생성")
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void constructor_WithinBoundNumber_CreatedNumber(int number) {
		assertThat(new Number(number))
			.isInstanceOf(Number.class);
	}

	@DisplayName("Number 생성자에 0이하 46이상의 정수 입력이 들어올 때 InvalidNumberException 발생")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void constructor_OutOfBoundNumber_ExceptionThrown(int number) {
		assertThatThrownBy(() -> new Number(number))
			.isInstanceOf(InvalidNumberException.class);
	}
}
