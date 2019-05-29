package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.exception.InvalidMoneyException;
import org.junit.jupiter.api.Test;

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
}
