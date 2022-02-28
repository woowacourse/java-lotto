package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {46, 50, 100})
	@DisplayName("최대 숫자 이상의 숫자를 압력 한 경우 예외 발생")
	void numberMoreThanUpperBound(int input) {
		assertThatThrownBy(() -> new LottoNumber(input))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("최소 숫자 이하의 숫자를 입력 한 경우 예외 발생")
	void numberLowerThanLowerBound() {
		assertThatThrownBy(() -> new LottoNumber(0))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
