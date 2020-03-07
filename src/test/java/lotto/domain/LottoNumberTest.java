package lotto.domain;

import lotto.exceptions.LottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@Test
	void of() {
		// given
		int input = 7;

		// when
		LottoNumber lottoNumber = LottoNumber.of(input);

		// then
		Assertions.assertThat(lottoNumber.getLottoNumber())
				.isEqualTo(input);
		Assertions.assertThat(lottoNumber == LottoNumber.of(input))
				.isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 61, -1})
	void of_LessThanOneOrMoreThanSixty_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			LottoNumber lottoNumber = LottoNumber.of(input);
		}).isInstanceOf(LottoNumberException.class)
				.hasMessageMatching("-?[0-9]+" + LottoNumberException.MESSAGE);
	}

}