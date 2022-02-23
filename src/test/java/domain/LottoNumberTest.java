package domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 24, 45})
	@DisplayName("로또 넘버가 정상적으로 생성되는 경우")
	void createLottoNumber(int lottoNumberValue) {
		LottoNumber lottoNumber = LottoNumber.createByInput(lottoNumberValue);

		assertThat(lottoNumber).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	@DisplayName("로또 넘버가 유효범위를 벗어나는 경우")
	void createLottoNumberNotInRange(int lottoNumberValue) {
		assertThatThrownBy(() ->
			LottoNumber.createByInput(lottoNumberValue))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
