package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class MoneyTest {
	@ParameterizedTest
	@ValueSource(ints = {-1000, 0, 999})
	void 한장도_못사는_돈(int value) {
		assertThatThrownBy(() -> new Money(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("부족");
	}

	@ParameterizedTest
	@ValueSource(ints = {1001, 1500, 1999})
	void 천원_단위가_아닌_돈(int value) {
		assertThatThrownBy(() -> new Money(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("천 원 단위");
	}
}
