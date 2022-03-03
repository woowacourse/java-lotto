package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoCounterTest {
	@Test
	void notDivideByUnitPrice() {
		assertThatThrownBy(() -> new LottoCounter(11, 0)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void zeroPrice() {
		assertThatThrownBy(() -> new LottoCounter(0, 0)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void moreThanTotalSize() {
		assertThatThrownBy(() -> new LottoCounter(1000, 3)).isInstanceOf(IllegalArgumentException.class);
	}
}
