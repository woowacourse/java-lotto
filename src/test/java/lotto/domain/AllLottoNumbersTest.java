package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class AllLottoNumbersTest {
	@Test
	void get() {
		// when
		List<LottoNumber> allLottoNumbers = AllLottoNumbers.getAll();

		// then
		List<LottoNumber> expected = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expected.add(AllLottoNumbers.getLottoNumber(i));
		}

		Assertions.assertThat(allLottoNumbers)
				.isEqualTo(expected);
	}


	@ParameterizedTest
	@ValueSource(ints = {0, 61, -1})
	void Number_LessThanOneOrMoreThanSixty_ShouldThrowException(int input) {
		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			AllLottoNumbers.getLottoNumber(input);
		}).isInstanceOf(LottoNumberIllegalArgumentException.class)
				.hasMessageMatching("-?[0-9]+" + LottoNumberIllegalArgumentException.MESSAGE );
	}
}