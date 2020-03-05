package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

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
		}).isInstanceOf(LottoNumberIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoNumberIllegalArgumentException.MESSAGE);
	}

	@Test
	void getAll() {
		// when
		List<LottoNumber> allLottoNumbers = LottoNumber.allList();

		// then
		List<LottoNumber> expected = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expected.add(LottoNumber.of(i));
		}

		Assertions.assertThat(allLottoNumbers)
				.isEqualTo(expected);
	}
}