package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrizeTest {
	@Test
	void getTotal() {
		Prize prize = new Prize(2_000_000_000L);
		long expected = 2_000_000_000L * 3L;

		assertThat(prize.multiply(3)).isEqualTo(expected);
	}
}