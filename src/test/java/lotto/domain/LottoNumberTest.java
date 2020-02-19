package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 61, -1})
	void Number_LessThanOneOrMoreThanSixty_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			LottoNumber lottoNumber = new LottoNumber(input);
		}).isInstanceOf(LottoNumberIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoNumberIllegalArgumentException.MESSAGE );
	}
}