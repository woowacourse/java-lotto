package lotto.domain;

import lotto.exceptions.LottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 7, 45})
	void of(int input) {
		// when
		LottoNumber lottoNumber = LottoNumber.of(input);

		// then
		Assertions.assertThat(lottoNumber.getLottoNumber())
				.isEqualTo(input);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 7, 45})
	void of_ShouldHaveSameReference(int input) {
		// when
		LottoNumber lottoNumber = LottoNumber.of(input);

		// then
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