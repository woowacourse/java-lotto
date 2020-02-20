package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrizeTest {
	@Test
	void getTotal() {
		Prize prize = Prize.of(2_000_000_000L);
		long expected = 2_000_000_000L * 3;
		assertThat(prize.multiply(3)).extracting("prize").isEqualTo(expected);
	}

	@Test
	void plus() {
		Prize prize = Prize.of(1000);
		assertThat(prize.plus(prize)).isEqualTo(Prize.of(2000));
	}

	@Test
	void findProfits() {
		Prize prize = Prize.of(10000);
		Money money = new Money(10000);
		assertThat(prize.findProfits(money)).isEqualTo(100);
	}
}