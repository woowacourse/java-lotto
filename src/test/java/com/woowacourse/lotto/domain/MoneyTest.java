package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.exception.InvalidMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
	@Test
	void create() {
		Money money = new Money(1000);
		assertEquals(new Money(1000), money);
	}

	@Test
	void invalidMoney() {
		assertThrows(InvalidMoneyException.class, () -> new Money(0));
		assertThrows(InvalidMoneyException.class, () -> new Money(1500));
	}

	@Test
	void checkCountOfLotto() {
		assertThat(new Money(3000).getCountOfLotto()).isEqualTo(3);
	}

	@Test
	void checkEarningsRate() {
		Money money = new Money(3000);
		assertThat(money.calculateEarningsRate(2_000_000_000)).isEqualTo(66_666_666);
	}
}
