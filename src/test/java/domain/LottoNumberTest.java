package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

	@DisplayName("문자를 입력했을 시 실패")
	@Test
	void input_data_string_fail() {
		assertThatThrownBy(() -> new LottoNumber("one"))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("숫자를 입력했을 시 성공")
	@Test
	void input_data_success() {
		assertThatCode(() -> new LottoNumber("45"))
			.doesNotThrowAnyException();
	}

	@DisplayName("빈문자열")
	@Test
	void input_data_empty_fail() {
		assertThatThrownBy(() -> new LottoNumber(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("범위 불만족 최대/최소")
	@ParameterizedTest
	@ValueSource(strings = {"0,46"})
	void range(String number) {
		assertThatThrownBy(() -> new LottoNumber(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void factory() {
		assertThatThrownBy(() -> LottoNumber.of(50))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 범위를 벗어난 숫자입니다.");
	}
}
