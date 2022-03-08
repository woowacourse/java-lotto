package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

	@DisplayName("숫자를 입력했을 시 성공")
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	void input_data_success(int number) {
		assertThatCode(() -> LottoNumber.of(number))
			.doesNotThrowAnyException();
	}

	@DisplayName("범위 불만족 최대/최소")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void range(int number) {
		assertThatThrownBy(() -> LottoNumber.of(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 범위를 벗어난 숫자입니다.");
	}
}
