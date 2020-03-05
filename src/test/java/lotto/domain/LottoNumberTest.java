package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 61, -1})
	void Number_LessThanOneOrMoreThanSixty_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			LottoNumber lottoNumber = new LottoNumber(input);
		}).isInstanceOf(LottoNumberIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoNumberIllegalArgumentException.MESSAGE);
	}

	@Test
	void getAll() {
		// when
		List<LottoNumber> allLottoNumbers = LottoNumber.getAll();

		// then
		List<LottoNumber> expected = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expected.add(new LottoNumber(i));
		}

		Assertions.assertThat(allLottoNumbers)
				.isEqualTo(expected);
	}
}