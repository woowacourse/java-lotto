package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import exception.LottoNumberRangeException;

class LottoNumberTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 46, 100})
	void rangeValidate(int number) {
		assertThatThrownBy(() -> {
			LottoNumber.get(number);
		}).isInstanceOf(LottoNumberRangeException.class)
			.hasMessage("로또번호는 1~45의 수가 필요합니다.");
	}
}