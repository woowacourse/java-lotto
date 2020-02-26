package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.LackOfMoneyException;

class LottoFactoryTest {

	@Test
	void createLottos() {
		// given
		Money purchaseMoney = new Money(999);
		// when
		// then
		assertThatThrownBy(() -> {
			LottoFactory.createLottos(purchaseMoney);
		}).isInstanceOf(LackOfMoneyException.class)
			.hasMessage("로또 비용이 부족합니다.");
	}
}