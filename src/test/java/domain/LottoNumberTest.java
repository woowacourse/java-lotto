package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 46, 100})
	@DisplayName("허용범위 내에 존재하지 않는 숫자를 압력 한 경우 예외 발생")
	void numberInRange(int input) {
		assertThatThrownBy(() -> new LottoNumber(input))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
