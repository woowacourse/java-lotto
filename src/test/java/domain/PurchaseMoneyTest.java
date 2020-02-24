package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.LackOfMoneyException;

class PurchaseMoneyTest {
	@Test
	void validate() {
		assertThatThrownBy(() -> {
			PurchaseMoney.create(999);
		}).isInstanceOf(LackOfMoneyException.class)
			.hasMessage("로또 비용이 부족합니다.");
	}
}