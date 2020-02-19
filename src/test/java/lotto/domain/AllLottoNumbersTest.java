package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AllLottoNumbersTest {
	@Test
	void get() {
		// when
		List<LottoNumber> allLottoNumbers = AllLottoNumbers.get();

		// then
		List<LottoNumber> expected = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expected.add(new LottoNumber(i));
		}

		Assertions.assertThat(allLottoNumbers)
				.isEqualTo(expected);
	}
}