package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

	@DisplayName("범위 불만족 최대")
	@Test
	void range_max() {
		assertThatThrownBy(() -> new LottoNumber("46"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("범위 불만족 최소")
	@Test
	void range_min() {
		assertThatThrownBy(() -> new LottoNumber("0"))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
