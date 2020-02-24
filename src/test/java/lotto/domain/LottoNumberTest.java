package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void 번호_범위(int value) {
		assertThatThrownBy(() -> new LottoNumber(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 번호는");
	}

	@ParameterizedTest
	@CsvSource(value = {"1 1 true", "1 2 false"}, delimiter = ' ')
	void 번호가_같은지(int value1, int value2, boolean expected) {
		assertThat(new LottoNumber(value1).equals(new LottoNumber(value2))).isEqualTo(expected);
	}
}
