package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.exception.InvalidMoneyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoMoneyTest {
	@Test
	void create() {
		LottoMoney lottoMoney = new LottoMoney(1000);
		assertEquals(new LottoMoney(1000), lottoMoney);
	}

	@Test
	void invalidMoney() {
		assertThrows(InvalidMoneyException.class, () -> new LottoMoney(0));
		assertThrows(InvalidMoneyException.class, () -> new LottoMoney(1500));
	}

	@Test
	void checkCountOfLotto() {
		assertThat(new LottoMoney(3000).getCountOfLotto()).isEqualTo(3);
	}

	@Test
	void checkEarningsRate() {
		LottoMoney lottoMoney = new LottoMoney(3000);
		assertThat(lottoMoney.calculateEarningsRate(2_000_000_000)).isEqualTo(66_666_666);
	}
}
