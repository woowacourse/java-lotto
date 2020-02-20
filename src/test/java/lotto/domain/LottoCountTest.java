package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoCountTest {
	@Test
	void validateCount() {
		assertThatThrownBy(() -> LottoCount.of(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"10,9,true", "10,10,false", "10,1,true"})
	void fullCountTest(int fullCount, int currentCount, boolean expected) {
		LottoCount count = LottoCount.of(fullCount);
		assertThat(count.isNonFullCount(currentCount)).isEqualTo(expected);
	}
}
