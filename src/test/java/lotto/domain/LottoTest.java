package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottoTest {
	@ParameterizedTest
	@CsvSource(value = {"1 true", "6 true", "0 false", "7 false"}, delimiter = ' ')
	void 로또_생성(int value, boolean expected) {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.contains(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 로또가_null이나_빈값인_경우(List<Integer> value) {
		assertThatThrownBy(() -> new Lotto(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
